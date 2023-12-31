package com.rishu.todo.dao;

import com.rishu.todo.helper.Helper;
import com.rishu.todo.models.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Component
@Repository
public class TodoDao {

    Logger logger= LoggerFactory.getLogger(TodoDao.class);
    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;
        // create table if does not exist
        String createTable="create table IF NOT EXISTS todos (id int primary key, title varchar(100) not null, " +
                "content varchar(500), status" +
                " varchar(50) not null," +
                "addedDate datetime, todoDate datetime )";
        int update =template.update(createTable);
        logger.info("table created {} ",update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    //save
    public ToDo savaToDO(ToDo todo){

        String insertQuery="insert into todos(id,title,content,status,addedDate,todoDate) values(?,?,?,?,?,?)";
        // Dynamic or parametrized query
        int rows = template.update(insertQuery,todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(),
                todo.getAddedDate(),
                todo.getToDoDate());
        logger.info("rows affected {}", rows);
        return todo;
    }
    //get single
    public ToDo getTodo(int id) throws ParseException {
        String query="select * from todos where id=?";
        //Map<String, Object> todoData = template.queryForMap(query, id);
        ToDo todo = template.queryForObject(query,new TodoRowMapper(),id);

//        ToDo todo=new ToDo();
//
//        todo.setId((int)todoData.get("id"));
//        todo.setTitle((String)todoData.get("title"));
//        todo.setContent((String)todoData.get("content"));
//        todo.setStatus((String)todoData.get("status"));
//        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
//        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));

        return todo;
    }
    //get all todos from DB
    public List<ToDo> getAllToDos(){
        String query="select * from todos";
        List<ToDo> todo=template.query(query,new TodoRowMapper());

       // List<Map<String,Object>> maps=template.queryForList(query);
//        List<ToDo> todos =maps.stream().map((map)->{
//            ToDo todo=new ToDo();
//
//            todo.setId((int)map.get("id"));
//            todo.setTitle((String)map.get("title"));
//            todo.setContent((String)map.get("content"));
//            todo.setStatus((String)map.get("status"));
//            try {
//                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//                todo.setToDoDate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
//
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            return todo;
//        }).collect(Collectors.toList());
        return todo;
    }
    //update
    public ToDo updateTodo(int id,ToDo newTodo){
        String query="update todos set title=?,content=?,status=?,addedDate=?,todoDate=? where id=?";
        int update=template.update(query,newTodo.getTitle(),newTodo.getContent(),newTodo.getStatus(),
                newTodo.getAddedDate(),newTodo.getToDoDate(),id);
        logger.info("Updated info {}",update);
        return newTodo;

    }
    //delete todo from DB
    public void deleteTodo(int id){
        String query="delete from todos where id=? ";
        int update=template.update(query,id);
        logger.info("Deleted {}",update);
    }

    //delete multiple
    public void deleteMultiple(int ids[]){

        String query="delete from todos where id=? ";
        //for each
        //template.batchUpdate(query,ids)
        int[] ints=template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id=ids[i];
                ps.setInt(1,id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for(int i:ints){
            logger.info("Deleted {}",i);
        }
    }

}
