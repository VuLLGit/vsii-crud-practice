package com.example.crud_practice.dto.request;

import com.example.crud_practice.entity.enums.ClothesStatus;
import jakarta.validation.constraints.*;

/**
 * DTO dùng để nhận dữ liệu Clothes từ client.
 * Áp dụng Bean Validation để kiểm tra dữ liệu đầu vào
 * trước khi xử lý nghiệp vụ.
 */
public class ClothesRequest {

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Min(value = 0, message = "Giá không được âm")
    private double price;

    @Min(value = 0, message = "Số lượng không được âm")
    private int quantity;

    @Size(max = 2000, message = "Mô tả tối đa 2000 ký tự")
    private String description;

    @NotNull(message = "Trạng thái không được để trống")
    private ClothesStatus status;

    private String imageUrl;

    // getter & setter
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

