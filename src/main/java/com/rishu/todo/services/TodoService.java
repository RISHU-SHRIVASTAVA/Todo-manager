package com.rishu.todo.services;

import com.rishu.todo.models.ToDo;

import java.text.ParseException;
import java.util.List;

public interface TodoService {
    public ToDo createToDo(ToDo todo);
    public List<ToDo> getAllTodos();
    public ToDo getToDo(int todoId) throws ParseException;
    public ToDo updateTodo(int todoId,ToDo todo);
    public void deleteToDo(int todoId);
}
