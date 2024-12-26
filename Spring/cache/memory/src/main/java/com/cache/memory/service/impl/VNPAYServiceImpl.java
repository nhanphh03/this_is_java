package com.cache.memory.service.impl;

import com.cache.memory.config.vnpay.VNPAYConfig;
import com.cache.memory.service.VNPayService;
import com.cache.memory.utils.VNPAYUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VNPAYServiceImpl implements VNPayService {

    private final VNPAYConfig vnPayConfig;

    public String createVnPayPayment() {
        String bankCode = "VNBANK";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(100000L * 100L));
        vnpParamsMap.put("vnp_BankCode", bankCode);
        vnpParamsMap.put("vnp_IpAddr", "127.0.0.1");
        //build query url
        String queryUrl = VNPAYUtils.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPAYUtils.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPAYUtils.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        return vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
    }

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public String callUrlAndGetResponse(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
