package com.sparta.project2.repository;

import com.sparta.project2.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {


}
