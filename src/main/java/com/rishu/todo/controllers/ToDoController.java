package com.rishu.todo.controllers;

import com.rishu.todo.models.ToDo;
import com.rishu.todo.services.TodoService;
import com.rishu.todo.services.impl.DaoTodoServiceImpl;
import com.rishu.todo.services.impl.TodoServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    Logger logger= LoggerFactory.getLogger(ToDoController.class);
    @Autowired
    private TodoService ts;
    Random random=new Random();

    //create
    @PostMapping()
    public ResponseEntity<ToDo> createTodoHandler(@RequestBody ToDo todo){
//        String str=null;
//        logger.info("{}",str.length());
       // Integer.parseInt("23csac");
        int id=random.nextInt(99999);
        todo.setId(id);
        Date currentDate=new Date();
        logger.info("Current Date {}:",currentDate);
        //create Date with system default current date
        todo.setAddedDate(currentDate);

         //create todo
        logger.info("Create Todo");

        //call service to create todo
        ToDo todo1=ts.createToDo(todo);
        System.out.println(todo.toString());
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<ToDo>> getAllTodoHandler(){
        List<ToDo> allTodos=ts.getAllTodos();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<ToDo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {

       ToDo todo= ts.getToDo(todoId);
       return ResponseEntity.ok(todo);
    }

    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<ToDo> updateToDoHandler(@RequestBody ToDo todoWithNewDetails,@PathVariable int todoId){
        ToDo todo= ts.updateTodo(todoId,todoWithNewDetails);
        return ResponseEntity.ok(todo);

    }

    //Delete handler
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        ts.deleteToDo(todoId);
        return ResponseEntity.ok("ToDo deleted");

    }

    //exception handler
//    @ExceptionHandler(value={NullPointerException.class, NumberFormatException.class})
//    @ExceptionHandler
//    public String nullPointerExceptionhandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        return "Null pointer exception : "+ex.getMessage();
//    }
    //Specific to controller
//    @ExceptionHandler
//    public String ExceptionHandler(Exception ex){
//        System.out.println(ex.getMessage());
//        return "Exception due to : "+ex;
//
//    }

}
