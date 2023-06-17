package com.rishu.todo.dao;

import com.rishu.todo.helper.Helper;
import com.rishu.todo.models.ToDo;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper<ToDo> {
    @Override
    public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ToDo todo=new ToDo();

        todo.setId(rs.getInt("id"));
        todo.setTitle(rs.getString("title"));
        todo.setContent(rs.getString("content"));
        todo.setStatus(rs.getString("status"));
        try {
            todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
            todo.setToDoDate(Helper.parseDate((LocalDateTime) rs.getObject("todoDate")));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return todo;


    }
}
