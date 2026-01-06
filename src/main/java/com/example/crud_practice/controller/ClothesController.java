package com.example.crud_practice.controller;

import com.example.crud_practice.dto.request.ClothesRequest;
import com.example.crud_practice.service.ClothesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller xử lý các request liên quan đến Clothes.
 * Cung cấp các API: lấy danh sách, xem chi tiết, thêm, sửa và xóa Clothes.
 */
@RestController
@RequestMapping("/api/clothes")
public class ClothesController {
    private static final String DELETE_SUCCESS = "Deleted successfully";

    private final ClothesService clothesService;

    @Autowired
    private ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    /**
     * Lấy danh sách Clothes có phân trang, sắp xếp và tìm kiếm.
     *
     * @param page   số trang (mặc định 0)
     * @param size   số phần tử mỗi trang (mặc định 10)
     * @param sortBy trường sắp xếp (mặc định id)
     * @param search từ khóa tìm kiếm (có thể null)
     * @return danh sách Clothes
     */
    @Operation(
            summary = "Get all clothes",
            description = "Retrieve a paginated list of clothes with sorting and search support"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clothes list retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<?> getAllClothes(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(required = false) String search) {
        return ResponseEntity.ok(clothesService.getAllClothes(page, size, sortBy, search));
    }

    /**
     * Lấy thông tin chi tiết Clothes theo id.
     *
     * @param clothesId id của Clothes
     * @return ClothesResponse nếu tồn tại
     */
    @Operation(
            summary = "Get clothes by ID",
            description = "Retrieve clothes details by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clothes found"),
            @ApiResponse(responseCode = "404", description = "Clothes not found")
    })
    @GetMapping("/{clothesId}")
    public ResponseEntity<?> getClothesById(@PathVariable("clothesId") Long clothesId) {
        return ResponseEntity.ok(clothesService.getClothesById(clothesId));
    }

    /**
     * Thêm mới Clothes.
     * Thực hiện validate dữ liệu đầu vào trước khi xử lý.
     *
     * @param clothesRequest dữ liệu Clothes từ client
     * @return ClothesResponse nếu thành công, lỗi nếu validate thất bại
     */
    @Operation(
            summary = "Add new clothes",
            description = "Create a new clothes item with input validation"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clothes created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/add")
    public ResponseEntity<?> addClothes(@RequestBody @Valid ClothesRequest clothesRequest) {
        return ResponseEntity.ok(clothesService.addClothes(clothesRequest));
    }

    /**
     * Chỉnh sửa thông tin Clothes theo id.
     * Thực hiện validate dữ liệu trước khi cập nhật.
     *
     * @param clothesRequest dữ liệu mới của Clothes
     * @param clothesId      id của Clothes cần chỉnh sửa
     * @return ClothesResponse sau khi cập nhật
     */
    @Operation(
            summary = "Update clothes",
            description = "Update clothes information by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clothes updated successfully"),
            @ApiResponse(responseCode = "400", description = "Clothes not found or invalid input")
    })
    @PatchMapping("/{clothesId}/edit")
    public ResponseEntity<?> editClothes(@RequestBody @Valid ClothesRequest clothesRequest,
                                         @PathVariable("clothesId") Long clothesId) {
        return ResponseEntity.ok(clothesService.editClothes(clothesRequest, clothesId));
    }

    /**
     * Xóa Clothes theo id.
     *
     * @param clothesId id của Clothes cần xóa
     * @return thông báo xóa thành công
     */
    @Operation(
            summary = "Delete clothes",
            description = "Delete clothes by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clothes deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Clothes not found")
    })
    @DeleteMapping("/{clothesId}/delete")
    public ResponseEntity<?> deleteClothes(@PathVariable("clothesId") Long clothesId) {
        clothesService.deleteClothes(clothesId);
        return ResponseEntity.ok(DELETE_SUCCESS);
    }
}

