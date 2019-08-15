package com.gy.security.controller;


import com.gy.security.domain.User;
import com.gy.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/userlogin")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String list1(Model model){
        List<User> users = userService.getUserList();
        System.out.println(users);
        model.addAttribute("users",users);
        //return new ModelAndView("users/list","userModel",model);
        return "users/list";
    }
    @RequestMapping("/list")
    public String list(Model model){
        List<User> users = userService.getUserList();
        System.out.println(users);
        model.addAttribute("users",users);
        //return new ModelAndView("users/list","userModel",model);
        return "users/list";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "users/userAdd"; //可直接去resources目录下找相关的文件
    }
    @RequestMapping("/add")
    public String add(User user){
        userService.save(user);
        return "redirect:/list";
    }
    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "users/userEdit";
    }
    @RequestMapping("/edit")
    public String edit(User user){
        userService.edit(user);
        return "redirect:/list";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        userService.delete(id);
        return "redirect:/list";
    }
}
