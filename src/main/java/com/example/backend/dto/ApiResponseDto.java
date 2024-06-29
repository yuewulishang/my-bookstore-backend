package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApiResponseDto {
    private boolean success;
    private String message;
    // 新增 setter
    // 新增 getter
    @Setter
    private Object data; // 新增字段

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
