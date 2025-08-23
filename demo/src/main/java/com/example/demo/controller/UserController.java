package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("/listPage")
    public List<User> listP(@RequestBody QueryPageParam query){

//        System.out.println(query);
//        System.out.println(query.getPageNum());
//        System.out.println(query.getPageSize());
//
        HashMap map = query.getParam();
        String name = map.get("name").toString();
//
//        System.out.println(map.get("name"));
        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, name);

        IPage result = userService.page(page, lambdaQueryWrapper);

        System.out.println(result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public List<User> listPC(@RequestBody QueryPageParam query){

        HashMap map = query.getParam();
        String name = map.get("name").toString();

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, name);

        IPage result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println(result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC1")
    public Result listPC1(@RequestBody QueryPageParam query){

        HashMap map = query.getParam();
        String name = map.get("name").toString();

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, name);

        IPage result = userService.pageCC(page, lambdaQueryWrapper);

        System.out.println(result.getTotal());

        return Result.success(result.getRecords(), result.getTotal());
    }
}
