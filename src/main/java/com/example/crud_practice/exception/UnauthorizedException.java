package com.example.crud_practice.exception;

/**
 * Exception biểu thị lỗi không được phép truy cập (HTTP 401 Unauthorized).
 * <p>
 * Thường dùng cho các trường hợp đăng nhập sai thông tin hoặc thiếu token.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Khởi tạo UnauthorizedException với thông điệp lỗi chi tiết.
     *
     * @param message mô tả lý do không được phép
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
