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

    private final static String ADD_USER_SQL = "INSERT INTO user (name, password, email, phone) VALUES(?,?,?,?)";
    private final static String DELETE_USER_SQL = "delete from user where user_id = ?  ";
    private final static String QUERY_ALL_USERS_SQL = "SELECT * FROM user ";
    private final static String GET_USER_SQL = "SELECT * FROM user where user_id = ? ";
    private final static String GET_USER_COUNT_SQL_BY_NAME = "SELECT count(*) FROM user where name = ? ";
    private final static String MATCH_COUNT_SQL = "select count(*) from user where name = ? and password = ? ";
    private final static String GET_USER_BY_NAME_SQL = "SELECT * FROM user where name = ? ";

    public ArrayList<User> getAllUsers() {
        final ArrayList<User> users = new ArrayList<User>();

        jdbcTemplate.query(QUERY_ALL_USERS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    User user = new User();
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

    public int deleteUser(long userId) {
        return jdbcTemplate.update(DELETE_USER_SQL, userId);
    }

    public User getUser(Long userId) {
        final User user = new User();
        jdbcTemplate.query(GET_USER_SQL, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
            }
        });
        return user;
    }

    public int getMatchCount(String name, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{name, password}, Integer.class);
    }

    public int userReg(String name, String password, String email, String phone) {
        int isExistUser = jdbcTemplate.queryForObject(GET_USER_COUNT_SQL_BY_NAME, new Object[]{name}, Integer.class);
        System.out.print(isExistUser);
        if (isExistUser > 0) {
            return 0;
        }
        return jdbcTemplate.update(ADD_USER_SQL, new Object[]{name, password, email, phone});
    }

    public User getUserByName(String name){
        final User user = new User();
        jdbcTemplate.query(GET_USER_BY_NAME_SQL, new Object[]{name}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
            }
        });
        return user;
    }

    public int userLogin(String name, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{name, password}, Integer.class);
    }

}
