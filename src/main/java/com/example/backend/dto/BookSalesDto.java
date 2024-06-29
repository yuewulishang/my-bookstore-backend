package com.example.backend.dto;

public class BookSalesDto {
    private String title;
    private long sales;

    // Constructor
    public BookSalesDto(String title, long sales) {
        this.title = title;
        this.sales = sales;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }
}
