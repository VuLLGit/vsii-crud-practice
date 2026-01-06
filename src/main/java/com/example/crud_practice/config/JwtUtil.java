package com.example.crud_practice.config;

import com.example.crud_practice.entity.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Tiện ích hỗ trợ tạo và xác thực JWT cho hệ thống.
 * <p>
 * Chịu trách nhiệm sinh token từ thông tin tài khoản và trích xuất username
 * từ token khi cần xác thực request.
 */
public class JwtUtil {
    private static final String SECRET = "jV0dVqz9M7h1fK3rP8s2W6x9Z4c1B7n0Q5t8L2y6R9u3E1a5C7k9M2p4T6v8X0z";
    private static final long EXPIRATION = 3600000; // 1 hour

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * Sinh JWT cho tài khoản đã xác thực.
     *
     * @param account tài khoản đã đăng nhập
     * @return chuỗi JWT đại diện cho phiên đăng nhập
     */
    public static String generateToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim("id", account.getId())
                .claim("role", account.getRole().name())
                .claim("perms", account.getRole().getPermissions().stream()
                        .map(Enum::name)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    /**
     * Xác thực và trích xuất username từ JWT.
     *
     * @param token chuỗi JWT từ client
     * @return username nếu token hợp lệ, ngược lại ném exception
     */
    public static String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // return username
    }
}
