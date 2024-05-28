package com.sparta.project2.service;

import com.sparta.project2.controller.TodoRequestDTO;
import com.sparta.project2.repository.Todo;
import com.sparta.project2.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    //할일 생성
    public Todo createTodo(TodoRequestDTO dto){

        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);

    }

    //할일 단건 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);

    }

    //할일 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());

    }
    //할일 수정
    public Todo updateTodo(long todoId, TodoRequestDTO dto) {
        Todo todo = checkPWAndGetTodo(todoId, dto.getPassword());

        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName( dto.getUserName());

        return todoRepository.save(todo);

    }



    public void deleteTodo(long todoId, String password) {
        Todo todo = checkPWAndGetTodo(todoId, password);

        todoRepository.delete(todo);


    }

    private Todo checkPWAndGetTodo(long todoId, String password) {
        Todo todo = getTodo(todoId);

        //비밀번호 체크
        if (todo.getPassword() !=null
                && !Objects.equals(todo.getPassword(), password)) {
            throw new IllegalArgumentException();
        }
        return todo;
    }
    
    
}
