package com.cache.memory.controller;

import com.cache.memory.service.CustomerService;
import com.cache.memory.service.impl.VNPAYServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApi {


    private final CustomerService customerService;
    private final VNPAYServiceImpl vnpayService;

    public CustomerApi(CustomerService customerService,
                       VNPAYServiceImpl vnpayService) {
        this.customerService = customerService;
        this.vnpayService = vnpayService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> getCustomerByName() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.countAllCustomers());
    }

    @PostMapping(value = "/vnpay/request")
    public ResponseEntity<?> payWithVNPayRequest() {
        return ResponseEntity.status(HttpStatus.OK).body(vnpayService.createVnPayPayment());
    }

    @GetMapping(value = "/vnpay/response")
    public ResponseEntity<?> payWithVNPayResponse(
            @RequestParam String vnp_Amount,
    @RequestParam String vnp_BankCode,
    @RequestParam String vnp_BankTranNo,
    @RequestParam String vnp_CardType,
    @RequestParam String vnp_OrderInfo,
    @RequestParam String vnp_PayDate,
    @RequestParam String vnp_ResponseCode,
    @RequestParam String vnp_TmnCode,
    @RequestParam String vnp_TransactionNo,
    @RequestParam String vnp_TransactionStatus,
    @RequestParam String vnp_TxnRef,
    @RequestParam String vnp_SecureHash) {
        System.out.println("vnp_Amount: " + vnp_Amount);
        System.out.println("vnp_BankCode: " + vnp_BankCode);
        System.out.println("vnp_BankTranNo: " + vnp_BankTranNo);
        System.out.println("vnp_CardType: " + vnp_CardType);
        System.out.println("vnp_OrderInfo: " + vnp_OrderInfo);
        System.out.println("vnp_PayDate: " + vnp_PayDate);
        System.out.println("vnp_ResponseCode: " + vnp_ResponseCode);
        System.out.println("vnp_TmnCode: " + vnp_TmnCode);
        System.out.println("vnp_TransactionNo: " + vnp_TransactionNo);
        System.out.println("vnp_TransactionStatus: " + vnp_TransactionStatus);
        System.out.println("vnp_TxnRef: " + vnp_TxnRef);
        System.out.println("vnp_SecureHash: " + vnp_SecureHash);

        return ResponseEntity.status(HttpStatus.OK).body(vnpayService.getResponse());
    }
}
