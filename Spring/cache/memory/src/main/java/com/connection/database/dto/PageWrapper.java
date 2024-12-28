package com.connection.database.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageWrapper<T> implements Serializable {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
