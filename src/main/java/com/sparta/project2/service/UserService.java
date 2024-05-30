package com.sparta.project2.service;

import com.sparta.project2.dto.LoginRequestDTO;
import com.sparta.project2.dto.SignupRequestDTO;
import com.sparta.project2.entity.User;
import com.sparta.project2.entity.UserRoleEnum;
import com.sparta.project2.jwt.JwtUtil;
import com.sparta.project2.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    private final String ADMIN_TOKEN = "admin";
    public User createUser(SignupRequestDTO dto) {
        String username = dto.getUsername();
        String nickname = dto.getNickname();
        String password = dto.getPassword();

        //회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("/중복된 사용자가 존재합니다.");
        }

        //회원 admin 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (dto.isAdmin()){
            if(!ADMIN_TOKEN.equals(dto.getAdminToken())){
                throw new IllegalArgumentException("/관리자 암호가 틀렸습니다");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, nickname, password, role);

        return userRepository.save(user);




    }

    public void loginUser(LoginRequestDTO dto, HttpServletResponse res) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("/회원을 찾을 수 없습니다.")
        );

        if(!Objects.equals(password, user.getPassword())){
            throw new IllegalArgumentException("/회원을 찾을 수 없습니다.");
        }

        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token, res);
    }
}
