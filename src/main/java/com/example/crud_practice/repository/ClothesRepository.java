package com.example.crud_practice.repository;

import com.example.crud_practice.entity.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository thao tác dữ liệu với entity Clothes.
 * Kế thừa JpaRepository để sử dụng các chức năng CRUD cơ bản.
 */
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    /**
     * Lấy danh sách Clothes theo từ khóa tìm kiếm trong tên.
     * Nếu keyword null thì trả về toàn bộ danh sách.
     *
     * @param search   từ khóa tìm kiếm theo tên Clothes
     * @param pageable thông tin phân trang
     * @return danh sách Clothes phù hợp điều kiện tìm kiếm
     */
    @Query("""
            SELECT c FROM Clothes c
            WHERE (:search IS NULL
            OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')))
    """)
    Page<Clothes> getAllSearchedClothes(@Param("search") String search, Pageable pageable);
}
