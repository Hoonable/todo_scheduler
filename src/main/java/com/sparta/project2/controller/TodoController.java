package com.sparta.project2.controller;


import com.sparta.project2.CommonResponse;
import com.sparta.project2.dto.TodoRequestDTO;
import com.sparta.project2.dto.TodoResponseDTO;
import com.sparta.project2.entity.Todo;
import com.sparta.project2.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    public final TodoService todoService;

    @PostMapping()
    public ResponseEntity<CommonResponse<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.createTodo(dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                        .msg("생성이 완료되었습니다.")
                        .statusCode(HttpStatus.OK.value())
                        .data(response)
                .build());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDTO>> getTodo(@PathVariable long todoId) {
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                .msg("단건 조회가 완료되었습니다.")
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build());

    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<TodoResponseDTO>>> getTodos() {
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDTO> response = todos.stream()
                .map(TodoResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(CommonResponse.<List<TodoResponseDTO>>builder()
                .msg("목록 조회가 완료되었습니다.")
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build());

    }

    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDTO>> putTodo(@PathVariable long todoId, @RequestBody TodoRequestDTO dto) {
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDTO response = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDTO>builder()
                .msg("수정이 완료되었습니다.")
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse>  deleteTodo(@PathVariable long todoId, @RequestBody TodoRequestDTO dto) {
        todoService.deleteTodo(todoId, dto.getPassword());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .msg("삭제가 완료되었습니다.")
                .statusCode(HttpStatus.OK.value())
                .build());

    }
}
