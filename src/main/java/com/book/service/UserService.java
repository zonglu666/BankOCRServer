package com.book.service;

import com.book.dao.UserDao;
import com.book.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ArrayList<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public User getUser(long userId){
        return userDao.getUser(userId);
    }

    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }
}
