package com.sparta.project2.service;


import com.sparta.project2.dto.CommentRequestDTO;
import com.sparta.project2.entity.Comment;
import com.sparta.project2.entity.Todo;
import com.sparta.project2.repository.CommentRepository;
import com.sparta.project2.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public Comment createComment(CommentRequestDTO dto){
        if(dto.getTodoId() == null||dto.getComment() ==null){
            throw new IllegalArgumentException("Invalid request");
        }// 선택 일정의 ID를 입력하지 않거나 댓글 내용 비어 있는경우 Exception

        var newComment = dto.toEntity();
        Todo todo = todoRepository.findById(dto.getTodoId()).orElseThrow(IllegalArgumentException::new);
        //일정이 DB에 없는경우 Exception
        todo.getCommentList().add(newComment);
        todoRepository.save(todo);
        return commentRepository.save(newComment);

    }
}
