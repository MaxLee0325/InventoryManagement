package com.example.demo.controller;


import com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
 *
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
    @Autowired
    private MybatisPlusLanguageDriverAutoConfiguration mybatisPlusLanguageDriverAutoConfiguration;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List<User> res = userService.lambdaQuery().eq(User::getNo, no).list();
        return !list().isEmpty() ? Result.success(res) : Result.fail();
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return userService.save(user) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateById(user) ? Result.success() : Result.fail();
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
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName, user.getName());
        }
        return Result.success(userService.list(lambdaQueryWrapper));
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
        String name = (String)map.get("name");
        String sex = (String)map.get("sex");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName, name);
        }

        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex, sex);
        }

        IPage result = userService.pageCC(page, lambdaQueryWrapper);

        return Result.success(result.getRecords(), result.getTotal());
    }
}
