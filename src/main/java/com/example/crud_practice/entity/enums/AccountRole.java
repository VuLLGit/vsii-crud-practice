package com.example.crud_practice.entity.enums;

import java.util.Set;

/**
 * Enum đại diện cho các vai trò (role) của tài khoản trong hệ thống.
 * <p>
 * Mỗi role đi kèm với một tập các permission tương ứng.
 */
public enum AccountRole {
    ADMIN("Admin", Set.of(Permission.CLOTHES_READ, Permission.CLOTHES_WRITE)),
    USER("User", Set.of(Permission.CLOTHES_READ));

    private final String value;
    private final Set<Permission> permissions;

    /**
     * Khởi tạo một giá trị role với tên hiển thị và danh sách permission.
     *
     * @param value       tên hiển thị của role
     * @param permissions tập các quyền (permission) gắn với role
     */
    AccountRole(String value, Set<Permission> permissions) {
        this.value = value;
        this.permissions = permissions;
    }

    /**
     * Lấy tên hiển thị của role.
     *
     * @return chuỗi tên role
     */
    public String getValue() {
        return value;
    }

    /**
     * Lấy danh sách permission gắn với role.
     *
     * @return tập các permission
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }
}
