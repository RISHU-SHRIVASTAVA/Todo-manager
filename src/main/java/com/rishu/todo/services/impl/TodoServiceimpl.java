package com.rishu.todo.services.impl;

import com.rishu.todo.exception.ResourceNotFoundException;
import com.rishu.todo.models.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//USING SPRING JDBC
//@Component
@Service

public class TodoServiceimpl implements com.rishu.todo.services.TodoService {
    Logger logger= LoggerFactory.getLogger(TodoServiceimpl.class);
    List<ToDo> todos=new ArrayList<>();
    //create todo method
    public ToDo createToDo(ToDo todo){
        todos.add(todo);
        logger.info("Todos {}", this.todos);
                return todo;

    }

    public List<ToDo> getAllTodos() {
        return todos;
    }

    public ToDo getToDo(int todoId) {
        //ToDo todo=todos.stream().filter(t->todoId==t.getId()).findAny().get();
        ToDo todo=
                todos.stream().filter(t->todoId==t.getId()).findAny().orElseThrow(()-> new ResourceNotFoundException(
                        "To do not found with givven ID", HttpStatus.NOT_FOUND));

        logger.info("TODO :{}",todo);
        return todo;
    }

    public ToDo updateTodo(int todoId,ToDo todo){
        List<ToDo> newUpdateList= todos.stream().map(t->{
            if(t.getId()==todoId){
                //perform update
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());

                return t;
            }
            else{
                return t;
            }
        }).collect(Collectors.toList());
        todos=newUpdateList;
        todo.setId(todoId);
        return todo;
    }

    public void deleteToDo(int todoId) {
        logger.info("Deleting ToDo");
        List<ToDo> newList = todos.stream().filter(t->t.getId()!=todoId).collect(Collectors.toList());
        todos=newList;
    }
}
