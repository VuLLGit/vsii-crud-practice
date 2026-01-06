package com.example.crud_practice.entity;

import com.example.crud_practice.entity.enums.ClothesStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clothes")
@Data
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Min(value = 0, message = "Giá không được âm")
    @Column(nullable = false)
    private double price;

    @Min(value = 0, message = "Số lượng không được âm")
    @Column(nullable = false)
    private int quantity;

    @Size(max = 2000, message = "Mô tả tối đa 2000 ký tự")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClothesStatus status;

    @Column(name = "image_url")
    private String imagUrl;
}
