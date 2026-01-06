package com.example.crud_practice.exception;

/**
 * Exception dùng để biểu thị lỗi request không hợp lệ.
 * Thường được sử dụng cho các lỗi nghiệp vụ hoặc dữ liệu đầu vào sai.
 * Exception này sẽ được xử lý và trả về HTTP 400 (Bad Request).
 */
public class BadRequestException extends RuntimeException {

    /**
     * Khởi tạo BadRequestException với message mô tả lỗi.
     *
     * @param message thông báo lỗi chi tiết
     */
    public BadRequestException(String message) {
        super(message);
    }
}


