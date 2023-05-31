package com.example.login_demo.controllers;

import com.example.login_demo.models.User;
import com.example.login_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class userController {
    @Autowired UserService userService;

    @GetMapping ("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.getAllUser();
        model.addAttribute("listUsers",listUsers);
        return "userManage";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add new user");
        return "addForm";
    }

    @PostMapping ("/users/save")
    public String createNew(User user, RedirectAttributes ra){
        userService.addUser(user);
        ra.addFlashAttribute("message","The user has been saved successfully !");
        return "redirect:/users/new";
    }

    @GetMapping("/users/edit/{uid}")
    public String showEditForm(@PathVariable("uid")Integer uid,Model model,RedirectAttributes ra){
        try{
            User user = userService.getUserById(uid);
            model.addAttribute("user",user);
            model.addAttribute("userid",uid);
            model.addAttribute("pageTitle","Edit user (user_id:" + uid + ")");
            return "editForm";
        }
        catch (Exception e){
            ra.addFlashAttribute("message","User can not be found !");
            return "redirect:/users";
        }
    }
    @PostMapping ("/users/put/{uid}")
    public String changeInfo(@PathVariable("uid")Integer uid,User user,RedirectAttributes ra){
        userService.updateUser(user,uid);
        ra.addFlashAttribute("message","User's information has been changed !");
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{uid}")
    public String delete(@PathVariable("uid")Integer uid,RedirectAttributes ra){
        try{
            userService.deleteUser(uid);
            return "redirect:/users";
        }
        catch (Exception e){
            ra.addFlashAttribute("message","User can not be found !");
            return "redirect:/users";
        }
    }
}
