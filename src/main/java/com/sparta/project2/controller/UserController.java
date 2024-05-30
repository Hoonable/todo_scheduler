package com.sparta.project2.controller;


import com.sparta.project2.CommonResponse;
import com.sparta.project2.dto.TodoRequestDTO;
import com.sparta.project2.dto.TodoResponseDTO;
import com.sparta.project2.dto.UserRequestDTO;
import com.sparta.project2.dto.UserResponseDTO;
import com.sparta.project2.entity.Todo;
import com.sparta.project2.entity.User;
import com.sparta.project2.service.TodoService;
import com.sparta.project2.service.UserService;
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
    public ResponseEntity<CommonResponse<UserResponseDTO>> postUser(@RequestBody @Valid UserRequestDTO dto, BindingResult bindingResult) {
        //validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            throw new IllegalArgumentException("invalid username or password");
        }



        User user = userService.createUser(dto);
        UserResponseDTO response = new UserResponseDTO(user);
        return ResponseEntity.ok().body(CommonResponse.<UserResponseDTO>builder()
                .msg("회원가입에 성공했습니다.")
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build());
    }
}
