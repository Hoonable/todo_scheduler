package com.sparta.project2.dto;

import com.sparta.project2.entity.User;
import com.sparta.project2.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SignupResponseDTO {

    private Long id;
    private String username;
    private String nickname;
    private String password;
    private UserRoleEnum role;
    private LocalDateTime createdAt;

    public SignupResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
