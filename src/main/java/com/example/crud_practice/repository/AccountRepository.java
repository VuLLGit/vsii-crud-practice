package com.example.crud_practice.repository;

import com.example.crud_practice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository thao tác với bảng tài khoản người dùng.
 * <p>
 * Cung cấp các method CRUD mặc định và truy vấn bổ sung theo username.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Tìm tài khoản theo username.
     *
     * @param username tên đăng nhập cần tìm
     * @return {@link Account} nếu tồn tại, ngược lại trả về null
     */
    Account findByUsername(String username);
}
