package com.cache.memory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CustomerDTO {

    private Long id;
    private String name;
    private int age;
    private String address;
}
