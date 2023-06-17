package com.rishu.todo;

import com.rishu.todo.dao.TodoDao;
import com.rishu.todo.models.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	Logger logger= LoggerFactory.getLogger(TodoManagerApplication.class);

	@Autowired
	private TodoDao todoDao;
	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Application started :");
//		JdbcTemplate template = todoDao.getTemplate();
//		logger.info("Template object :{}", template);
//		logger.info("Template object : {}", template.getDataSource());


//		ToDo todo = new ToDo();
//		todo.setId(124);
//		todo.setTitle("Shashank");
//		todo.setContent("Python");
//		todo.setStatus("Done");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//
//		todoDao.savaToDO(todo);

//		ToDo todo =todoDao.getTodo(123);
//		logger.info("Rows Affected {}",todo);
//
//		todo.setTitle("Shash");
//		todo.setContent("Pytho");
//		todo.setStatus("Don");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//		todoDao.updateTodo(124,todo);

//		List<ToDo> allToDos = todoDao.getAllToDos();
//		logger.info("All todos {}",allToDos);

		//todoDao.deleteTodo(124);
//		todoDao.deleteMultiple(new int[]{2323,45});
	}
}
