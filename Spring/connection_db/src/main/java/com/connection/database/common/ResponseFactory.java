package com.connection.database.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public ResponseEntity<Object> success(Object data) {
        return ResponseEntity.ok(new Response("Success", "", data));
    }

    public ResponseEntity<?> error(String data, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(new ResponseError("Error", data, 500));
    }
}
