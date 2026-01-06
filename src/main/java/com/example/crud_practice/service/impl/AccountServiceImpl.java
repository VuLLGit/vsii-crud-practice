package com.example.crud_practice.service.impl;

import com.example.crud_practice.dto.request.LoginRequest;
import com.example.crud_practice.dto.request.RegisterRequest;
import com.example.crud_practice.entity.Account;
import com.example.crud_practice.entity.enums.AccountRole;
import com.example.crud_practice.exception.ConflictException;
import com.example.crud_practice.exception.ResourceNotFoundException;
import com.example.crud_practice.exception.UnauthorizedException;
import com.example.crud_practice.repository.AccountRepository;
import com.example.crud_practice.service.AccountService;
import com.example.crud_practice.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Triển khai nghiệp vụ liên quan đến tài khoản người dùng.
 * <p>
 * Bao gồm đăng ký, đăng nhập và truy vấn tài khoản theo username.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private static final String USERNAME_EXISTS = "Username exists";

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Khởi tạo AccountServiceImpl với các dependency cần thiết.
     *
     * @param accountRepository repository thao tác với bảng tài khoản
     * @param passwordEncoder   công cụ mã hóa mật khẩu
     */
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Đăng ký tài khoản mới cho người dùng.
     * <p>
     * Kiểm tra trùng username, mã hóa mật khẩu và lưu với role mặc định USER.
     *
     * @param registerRequest thông tin đăng ký tài khoản
     */
    @Override
    public void register(RegisterRequest registerRequest) {
        if (accountRepository.findAll().stream().anyMatch(account -> account.getUsername().equals(registerRequest.getUsername()))) {
            throw new ConflictException(USERNAME_EXISTS);
        }
        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );
        account.setRole(AccountRole.USER);
        accountRepository.save(account);
    }

    /**
     * Đăng nhập hệ thống và sinh JWT nếu thông tin hợp lệ.
     *
     * @param loginRequest thông tin đăng nhập (username, password)
     * @return JWT token cho phiên đăng nhập
     */
    @Override
    public String login(LoginRequest loginRequest) {
        Account account = accountRepository.findByUsername(loginRequest.getUsername());
        if (account == null) {
            throw new ResourceNotFoundException("Wrong username or password");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            throw new UnauthorizedException("Wrong username or password");
        }

        return JwtUtil.generateToken(account);
    }

    /**
     * Tìm kiếm tài khoản theo username.
     *
     * @param username tên đăng nhập
     * @return {@link Account} nếu tồn tại, ngược lại null
     */
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
