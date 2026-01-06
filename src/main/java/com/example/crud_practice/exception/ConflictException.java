package com.example.crud_practice.exception;

/**
 * Exception biểu thị xung đột dữ liệu (HTTP 409 Conflict).
 * <p>
 * Thường dùng khi tạo mới hoặc cập nhật dữ liệu vi phạm ràng buộc duy nhất.
 */
public class ConflictException extends RuntimeException {

    /**
     * Khởi tạo ConflictException với thông điệp lỗi chi tiết.
     *
     * @param message mô tả lỗi xung đột
     */
    public ConflictException(String message) {
        super(message);
    }
}
