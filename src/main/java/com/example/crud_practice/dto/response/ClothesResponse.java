package com.example.crud_practice.dto.response;

import com.example.crud_practice.entity.enums.ClothesStatus;

/**
 * DTO dùng để trả dữ liệu Clothes về cho client.
 * Chứa thông tin chi tiết của Clothes sau khi xử lý nghiệp vụ.
 */
public class ClothesResponse {
    private Long id;

    private String name;

    private double price;

    private int quantity;

    private String description;

    private ClothesStatus status;

    private String imageUrl;

    //getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClothesStatus getStatus() {
        return status;
    }

    public void setStatus(ClothesStatus status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
