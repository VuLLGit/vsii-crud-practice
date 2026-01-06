package com.example.crud_practice.service.impl;

import com.example.crud_practice.dto.request.ClothesRequest;
import com.example.crud_practice.dto.response.ClothesResponse;
import com.example.crud_practice.entity.Clothes;
import com.example.crud_practice.exception.ResourceNotFoundException;
import com.example.crud_practice.repository.ClothesRepository;
import com.example.crud_practice.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Triển khai ClothesService.
 * Xử lý nghiệp vụ và thao tác dữ liệu Clothes thông qua ClothesRepository.
 */
@Service
public class ClothesServiceImpl implements ClothesService {
    private static final String CLOTHES_NOT_FOUND = "Clothes not found";

    private final ClothesRepository clothesRepository;

    @Autowired
    private ClothesServiceImpl(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    /**
     * Chuyển đổi entity Clothes sang ClothesResponse DTO.
     *
     * @param clothes entity Clothes
     * @return ClothesResponse tương ứng
     */
    private ClothesResponse MapToDTO(Clothes clothes) {
        ClothesResponse clothesResponse = new ClothesResponse();
        clothesResponse.setId(clothes.getId());
        clothesResponse.setName(clothes.getName());
        clothesResponse.setPrice(clothes.getPrice());
        clothesResponse.setQuantity(clothes.getQuantity());
        clothesResponse.setDescription(clothes.getDescription());
        clothesResponse.setStatus(clothes.getStatus());
        clothesResponse.setImageUrl(clothes.getImagUrl());
        return clothesResponse;
    }

    /**
     * Lấy danh sách Clothes có phân trang, sắp xếp và tìm kiếm theo từ khóa.
     *
     * @param page   số trang (bắt đầu từ 0)
     * @param size   số phần tử mỗi trang
     * @param sortBy trường dùng để sắp xếp
     * @param search từ khóa tìm kiếm (có thể null hoặc rỗng)
     * @return danh sách Clothes dạng Page
     */
    @Override
    public Page<Clothes> getAllClothes(int page, int size, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

        Page<Clothes> clothes;
        if (search == null || search.isBlank()) {
            clothes = clothesRepository.findAll(pageable);
        } else {
            clothes = clothesRepository.getAllSearchedClothes(search.trim(), pageable);
        }

        return clothes;
    }

    /**
     * Lấy thông tin chi tiết Clothes theo id.
     *
     * @param id id của Clothes
     * @return ClothesResponse nếu tồn tại, exception nếu không tìm thấy
     */
    @Override
    public ClothesResponse getClothesById(Long id) {
        Clothes clothes = clothesRepository.findById(id).orElse(null);
        if (clothes == null) {
            throw new ResourceNotFoundException(CLOTHES_NOT_FOUND);
        }
        return MapToDTO(clothes);
    }

    /**
     * Thêm mới một Clothes.
     *
     * @param clothesRequest dữ liệu Clothes từ client
     * @return ClothesResponse sau khi thêm thành công
     */
    @Override
    public ClothesResponse addClothes(ClothesRequest clothesRequest) {
        Clothes clothes = new Clothes();

        clothes.setName(clothesRequest.getName());
        clothes.setPrice(clothesRequest.getPrice());
        clothes.setQuantity(clothesRequest.getQuantity());
        clothes.setDescription(clothesRequest.getDescription());
        clothes.setStatus(clothesRequest.getStatus());
        clothes.setImagUrl(clothesRequest.getImageUrl());

        clothes = clothesRepository.save(clothes);
        return MapToDTO(clothes);
    }

    /**
     * Chỉnh sửa thông tin Clothes theo id.
     *
     * @param clothesRequest dữ liệu mới của Clothes
     * @param clothesId      id của Clothes cần chỉnh sửa
     * @return ClothesResponse sau khi cập nhật, exception nếu ko tồn tại
     */
    @Override
    public ClothesResponse editClothes(ClothesRequest clothesRequest, Long clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId).orElse(null);

        if (clothes == null) {
            throw new ResourceNotFoundException(CLOTHES_NOT_FOUND);
        }

        clothes.setName(clothesRequest.getName());
        clothes.setPrice(clothesRequest.getPrice());
        clothes.setQuantity(clothesRequest.getQuantity());
        clothes.setDescription(clothesRequest.getDescription());
        clothes.setStatus(clothesRequest.getStatus());
        clothes.setImagUrl(clothesRequest.getImageUrl());

        clothes = clothesRepository.save(clothes);
        return MapToDTO(clothes);
    }

    /**
     * Xóa Clothes theo id.
     *
     * @param id id của Clothes cần xóa
     */
    @Override
    public void deleteClothes(Long id) {
        clothesRepository.deleteById(id);
    }
}
