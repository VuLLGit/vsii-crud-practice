package com.example.crud_practice.config;

import com.example.crud_practice.entity.enums.PermissionEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Cấu hình Spring Security cho ứng dụng.
 * <p>
 * Khởi tạo PasswordEncoder, cấu hình phân quyền cho các endpoint
 * và đăng ký {@link JwtFilter} vào chuỗi filter của Spring Security.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Khởi tạo bean {@link PasswordEncoder} dùng để mã hóa mật khẩu người dùng.
     *
     * @return đối tượng PasswordEncoder sử dụng BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Cấu hình chuỗi filter và rule bảo mật cho các request HTTP.
     *
     * @param http      đối tượng cấu hình HttpSecurity
     * @param jwtFilter filter xử lý JWT cho mỗi request
     * @return {@link SecurityFilterChain} đã cấu hình
     * @throws Exception nếu có lỗi trong quá trình cấu hình
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho API
                .authorizeHttpRequests(auth -> {
                    // mở cho auth
                    auth.requestMatchers("/api/auth/**").permitAll();

                    // tự động map permission -> API
                    for (PermissionEnum permission : PermissionEnum.values()) {
                        HttpMethod method = HttpMethod.valueOf(permission.getMethod());
                        auth.requestMatchers(method, permission.getUrl()).hasAuthority(permission.name());
                    }

                    // các request còn lại yêu cầu đăng nhập hoặc cho phép tùy cấu hình
                    auth.anyRequest().permitAll();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
