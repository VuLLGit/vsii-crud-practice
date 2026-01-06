package com.example.crud_practice.entity.enums;

/**
 * Enum định nghĩa trạng thái của Clothes.
 * Dùng để phân biệt Clothes đang hoạt động hoặc không hoạt động.
 */
public enum ClothesStatus {
    ACTIVE("Active"), INACTIVE("Inactive");

    private final String value;

    ClothesStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
