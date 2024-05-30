package com.sparta.project2.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @Size(min=4, max=10)
    @Pattern(regexp = "^[a-z0-9]*$")
    private String username;
    //최소 4  최대 10 길이의 알파벳 소문자와 숫자로 구성된 username

    private String nickname;
    @Size(min=8, max=15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;
    //최소 8 최대 15 길이의 알파벳 대소문자와 숫자로 구성된 password


    private boolean admin = false;
    private String adminToken = "";
}
