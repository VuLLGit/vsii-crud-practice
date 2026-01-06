package com.example.crud_practice.entity;

import com.example.crud_practice.entity.enums.AccountRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entity đại diện cho tài khoản người dùng trong hệ thống.
 * <p>
 * Lưu trữ thông tin đăng nhập và role để phục vụ cho việc xác thực và phân quyền.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountRole role;

    /**
     * Lấy danh sách quyền (authorities) của tài khoản.
     * <p>
     * Chuyển đổi các permission và role sang {@link GrantedAuthority}
     * để Spring Security sử dụng trong quá trình kiểm tra quyền.
     *
     * @return tập các GrantedAuthority tương ứng với tài khoản
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        role.getPermissions().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.name()))
        );
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }
}
