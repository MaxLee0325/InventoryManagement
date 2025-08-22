package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2025-08-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return userService.save(user);
    }

    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }

    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }

    @PostMapping("/listP")
    public List<User> listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, user.getName());
        return userService.list(lambdaQueryWrapper);
    }
}
