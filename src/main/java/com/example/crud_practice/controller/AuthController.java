package com.example.crud_practice.controller;

import com.example.crud_practice.dto.request.LoginRequest;
import com.example.crud_practice.dto.request.RegisterRequest;
import com.example.crud_practice.exception.ConflictException;
import com.example.crud_practice.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller xử lý các API xác thực người dùng (đăng ký, đăng nhập).
 * <p>
 * Cung cấp endpoint cho việc tạo tài khoản mới và sinh JWT khi đăng nhập thành công.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final String REGISTER_SUCCESS = "Register successfully";

    private final AccountService accountService;

    /**
     * Khởi tạo AuthController với dependency AccountService.
     *
     * @param accountService service xử lý nghiệp vụ tài khoản
     */
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * API đăng ký tài khoản mới.
     *
     * @param registerRequest thông tin đăng ký (username, password, confirmPassword)
     * @return thông báo đăng ký thành công
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new ConflictException("Password và Confirm password không trùng khớp");
        }
        accountService.register(registerRequest);
        return ResponseEntity.ok(REGISTER_SUCCESS);
    }

    /**
     * API đăng nhập hệ thống.
     *
     * @param loginRequest thông tin đăng nhập (username, password)
     * @return JWT token nếu đăng nhập thành công
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = accountService.login(loginRequest);
        return ResponseEntity.ok(token);
    }
}
