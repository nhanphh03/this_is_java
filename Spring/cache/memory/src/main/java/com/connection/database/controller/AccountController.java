package com.connection.database.controller;

import com.connection.database.dto.AccountDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class AccountController {

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody @Valid AccountDTO accountDTO) {
        // Nếu dữ liệu không hợp lệ, Spring sẽ tự động trả về lỗi 400 với thông tin chi tiết
        return ResponseEntity.ok("Transaction is valid: " + accountDTO.getCustomerName());
    }
}
