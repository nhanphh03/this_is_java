package com.connection.database.service.impl;

import com.connection.database.config.vnpay.VNPAYConfig;
import com.connection.database.dto.TransactionRequestToVNPay;
import com.connection.database.entity.Transaction;
import com.connection.database.repository.jpa.TransactionJPARepository;
import com.connection.database.service.VNPayService;
import com.connection.database.utils.VNPAYUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VNPAYServiceImpl implements VNPayService {

    private final VNPAYConfig vnPayConfig;
    private final TransactionJPARepository transactionJPARepository;

    public String createVnPayPayment(TransactionRequestToVNPay transactionRequest) {

        Transaction transaction = Transaction.builder()
                        .amount(transactionRequest.getAmount())
                        .accountID(transactionRequest.getAccountId())
//                        .transactionDate(LocalDateTime.now())
                        .transactionType(transactionRequest.getTransactionType())
                        .status("pending")
                        .currency("vn")
                        .location(transactionRequest.getLocation())
                        .build();
        //lưu vào db
        Transaction result = transactionJPARepository.save(transaction);

        //todo gọi sang hệ thống vnpay
        String bankCode = "VNBANK";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", transactionRequest.getAmount() + "00");
        vnpParamsMap.put("vnp_BankCode", bankCode);
        vnpParamsMap.put("vnp_TxnRef", result.getTransactionID().toString());
        vnpParamsMap.put("vnp_IpAddr", "127.0.0.1");
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang:" + result.getTransactionID().toString() );
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
