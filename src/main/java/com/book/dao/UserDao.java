package com.book.dao;

import com.book.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_USER_SQL="INSERT INTO user VALUES(NULL ,?)";
    private final static String DELETE_USER_SQL="delete from user where user_id = ?  ";
    private final static String EDIT_USER_SQL="update user set name= ? where user_id= ? ";
    private final static String QUERY_ALL_USERS_SQL="SELECT * FROM user ";
    private final static String GET_USER_SQL="SELECT * FROM user where user_id = ? ";

    public ArrayList<User> getAllUsers(){
        final ArrayList<User> users=new ArrayList<User>();

        jdbcTemplate.query(QUERY_ALL_USERS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    User user =new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setEmail(resultSet.getString("email"));
                    users.add(user);
                }
            }
        });
        return users;
    }

    public int deleteUser(long userId){

        return jdbcTemplate.update(DELETE_USER_SQL,userId);
    }

    public int addUser(User user){
        String name=user.getName();
        return jdbcTemplate.update(ADD_USER_SQL,new Object[]{name});
    }

    public User getUser(Long userId){
        final User user =new User();
        jdbcTemplate.query(GET_USER_SQL, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
            }
        });
        return user;
    }

}
