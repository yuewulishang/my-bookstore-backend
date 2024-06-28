package com.example.backend.dto;

public class ApiResponseDto {
    private boolean success;
    private String message;
    private Object data; // 新增字段

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() { // 新增 getter
        return data;
    }

    public void setData(Object data) { // 新增 setter
        this.data = data;
    }
}
