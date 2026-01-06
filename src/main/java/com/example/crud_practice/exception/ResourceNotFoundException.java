package com.example.crud_practice.exception;

/**
 * Exception dùng để biểu thị lỗi không tìm thấy tài nguyên.
 * Thường được ném ra khi truy vấn dữ liệu theo id nhưng không tồn tại.
 * Exception này sẽ được xử lý và trả về HTTP 404 (Not Found).
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Khởi tạo ResourceNotFoundException với message mô tả lỗi.
     *
     * @param message thông báo lỗi chi tiết
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}


