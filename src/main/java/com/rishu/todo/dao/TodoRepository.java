package com.rishu.todo.dao;


import com.rishu.todo.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<ToDo,Integer> {
}
