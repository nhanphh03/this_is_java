package com.cache.memory.config.dto;

import com.cache.memory.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageWrapper implements Serializable {
    private List<Customer> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
