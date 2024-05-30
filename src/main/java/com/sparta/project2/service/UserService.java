package com.sparta.project2.service;

import com.sparta.project2.dto.UserRequestDTO;
import com.sparta.project2.entity.User;
import com.sparta.project2.entity.UserRoleEnum;
import com.sparta.project2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final String ADMIN_TOKEN = "admin";
    public User createUser(UserRequestDTO dto) {
        String username = dto.getUsername();
        String nickname = dto.getNickname();
        String password = dto.getPassword();

        //회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        //회원 admin 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (dto.isAdmin()){
            if(!ADMIN_TOKEN.equals(dto.getAdminToken())){
                throw new IllegalArgumentException("관리자 암호가 틀렸습니다");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, nickname, password, role);

        return userRepository.save(user);




    }
}
