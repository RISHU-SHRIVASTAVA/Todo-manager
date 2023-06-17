package com.rishu.todo.services.impl;

import com.rishu.todo.dao.TodoDao;
import com.rishu.todo.models.ToDo;
import com.rishu.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


@Service
//@Primary
public class DaoTodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;
    @Override
    public ToDo createToDo(ToDo todo) {
        return todoDao.savaToDO(todo);
    }

    @Override
    public List<ToDo> getAllTodos() {
        return todoDao.getAllToDos();
    }

    @Override
    public ToDo getToDo(int todoId) throws ParseException {
        return todoDao.getTodo(todoId);
    }

    @Override
    public ToDo updateTodo(int todoId, ToDo todo) {
        return todoDao.updateTodo(todoId,todo);
    }

    @Override
    public void deleteToDo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}
