package com.example.crud_practice.service;

import com.example.crud_practice.dto.request.LoginRequest;
import com.example.crud_practice.dto.request.RegisterRequest;
import com.example.crud_practice.entity.Account;

/**
 * Service định nghĩa các nghiệp vụ liên quan đến tài khoản người dùng.
 */
public interface AccountService {

    /**
     * Đăng ký tài khoản mới.
     *
     * @param registerRequest thông tin đăng ký tài khoản
     */
    void register(RegisterRequest registerRequest);

    /**
     * Đăng nhập và sinh JWT cho người dùng.
     *
     * @param loginRequest thông tin đăng nhập
     * @return JWT token nếu đăng nhập thành công
     */
    String login(LoginRequest loginRequest);

    /**
     * Tìm tài khoản theo username.
     *
     * @param username tên đăng nhập
     * @return {@link Account} nếu tồn tại, ngược lại null
     */
    Account findByUsername(String username);
}
