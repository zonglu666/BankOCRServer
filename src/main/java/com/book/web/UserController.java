package com.book.web;

import com.book.domain.User;
import com.book.service.UserService;
import com.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/allusers.html")
    public ModelAndView allUser(){
        ArrayList<User> users=userService.getAllUsers();
        ModelAndView modelAndView=new ModelAndView("admin_users");
        modelAndView.addObject("users",users);
        return modelAndView;
    }
}
