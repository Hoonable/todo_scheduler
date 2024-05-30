package com.sparta.project2.controller;


import com.sparta.project2.CommonResponse;
import com.sparta.project2.dto.LoginRequestDTO;
import com.sparta.project2.dto.SignupRequestDTO;
import com.sparta.project2.dto.SignupResponseDTO;
import com.sparta.project2.entity.User;
import com.sparta.project2.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignupResponseDTO>> signupUser(@RequestBody @Valid SignupRequestDTO dto, BindingResult bindingResult) {
        //validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            throw new IllegalArgumentException("/invalid username or password");
        }



        User user = userService.createUser(dto);
        SignupResponseDTO response = new SignupResponseDTO(user);
        return ResponseEntity.ok().body(CommonResponse.<SignupResponseDTO>builder()
                .msg("회원가입에 성공했습니다.")
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<SignupResponseDTO>> loginUser(@RequestBody LoginRequestDTO dto, HttpServletResponse res) {

          userService.loginUser(dto, res);

         return ResponseEntity.ok().body(CommonResponse.<SignupResponseDTO>builder()
                .msg("로그인에 성공했습니다.")
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
