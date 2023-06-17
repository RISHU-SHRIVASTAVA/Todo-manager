package com.rishu.todo.services.impl;

import com.rishu.todo.dao.TodoRepository;
import com.rishu.todo.models.ToDo;
import com.rishu.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
@Service
@Primary
public class TodoJpaServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    Logger logger= LoggerFactory.getLogger(TodoJpaServiceImpl.class);
    @Override
    public ToDo createToDo(ToDo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<ToDo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public ToDo getToDo(int todoId) throws ParseException {
        return todoRepository.findById(todoId).orElseThrow(()->new RuntimeException("Not Found"));
    }
    @Override
    public ToDo updateTodo(int todoId, ToDo todo) {
        ToDo oldUser = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Not Found"));
        oldUser.setStatus(todo.getStatus());
        oldUser.setAddedDate(todo.getAddedDate());
        oldUser.setContent(todo.getContent());
        oldUser.setToDoDate(todo.getToDoDate());
        return todoRepository.save(oldUser);
    }
    @Override
    public void deleteToDo(int todoId) {
        ToDo user_found = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Not Found"));
        todoRepository.delete(user_found);
    }
}
