package com.example.crud_practice.service;

import com.example.crud_practice.dto.request.ClothesRequest;
import com.example.crud_practice.dto.response.ClothesResponse;
import com.example.crud_practice.entity.Clothes;
import org.springframework.data.domain.Page;

/**
 * Service cung cấp các chức năng nghiệp vụ liên quan đến Clothes.
 * Bao gồm: lấy danh sách, tìm kiếm, thêm, sửa và xóa Clothes.
 */
public interface ClothesService {

    /**
     * Lấy danh sách Clothes có phân trang, sắp xếp và tìm kiếm theo từ khóa.
     *
     * @param page   số trang (bắt đầu từ 0)
     * @param size   số phần tử mỗi trang
     * @param sortBy trường dùng để sắp xếp
     * @param search từ khóa tìm kiếm (có thể null hoặc rỗng)
     * @return danh sách Clothes dạng Page
     */
    Page<Clothes> getAllClothes(int page, int size, String sortBy, String search);

    /**
     * Lấy thông tin chi tiết Clothes theo id.
     *
     * @param id id của Clothes
     * @return ClothesResponse nếu tồn tại, exception nếu không tìm thấy
     */
    ClothesResponse getClothesById(Long id);

    /**
     * Thêm mới một Clothes.
     *
     * @param clothesRequest dữ liệu Clothes từ client
     * @return ClothesResponse sau khi thêm thành công
     */
    ClothesResponse addClothes(ClothesRequest clothesRequest);

    /**
     * Chỉnh sửa thông tin Clothes theo id.
     *
     * @param clothesRequest dữ liệu mới của Clothes
     * @param clothesId      id của Clothes cần chỉnh sửa
     * @return ClothesResponse sau khi cập nhật, exception nếu không tồn tại
     */
    ClothesResponse editClothes(ClothesRequest clothesRequest, Long clothesId);

    /**
     * Xóa Clothes theo id.
     *
     * @param id id của Clothes cần xóa
     */
    void deleteClothes(Long id);
}
