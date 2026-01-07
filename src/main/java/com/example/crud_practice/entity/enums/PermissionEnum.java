package com.example.crud_practice.entity.enums;

/**
 * Enum đại diện cho các permission (quyền cụ thể) trong hệ thống.
 * <p>
 * Mỗi permission gắn với HTTP method, URL pattern và mô tả để có thể
 * cấu hình phân quyền tự động trong SecurityConfig (không hard-code từng rule).
 */
public enum PermissionEnum {

    // Clothes Permissions
    CLOTHES_READ("GET", "/api/clothes/**", "Quyền xem quần áo"),
    CLOTHES_WRITE("POST", "/api/clothes/**", "Quyền tạo/sửa/xóa quần áo"),

    // Encryption Permissions
    ENCRYPTION_AES_ENCRYPT("POST", "/api/encryption/aes/encrypt", "Quyền mã hóa AES"),
    ENCRYPTION_AES_DECRYPT("POST", "/api/encryption/aes/decrypt", "Quyền giải mã AES"),
    ENCRYPTION_RSA_ENCRYPT("POST", "/api/encryption/rsa/encrypt", "Quyền mã hóa RSA"),
    ENCRYPTION_RSA_DECRYPT("POST", "/api/encryption/rsa/decrypt", "Quyền giải mã RSA"),
    ENCRYPTION_RSA_PUBLIC_KEY("GET", "/api/encryption/rsa/public-key", "Quyền lấy RSA public key"),
    ENCRYPTION_HYBRID_ENCRYPT("POST", "/api/encryption/hybrid/encrypt-aes-key", "Quyền mã hóa AES key bằng RSA");

    private final String method;
    private final String url;
    private final String description;

    PermissionEnum(String method, String url, String description) {
        this.method = method;
        this.url = url;
        this.description = description;
    }

    /**
     * HTTP method của API mà permission này áp dụng.
     */
    public String getMethod() {
        return method;
    }

    /**
     * URL pattern của API mà permission này áp dụng.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Mô tả permission (phục vụ hiển thị/ghi log/tài liệu).
     */
    public String getDescription() {
        return description;
    }
}


