package com.chen.thylealeaf.security.controller;

import com.chen.thylealeaf.security.mapper.UserMapper;
import com.chen.thylealeaf.security.model.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 程强
 * @date 2020年04月23日 14:30
 * @Description:
 */
@Controller
public class TestController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/findUser/")
    @ResponseBody
    public User getUserByUsername(){
        User root = userMapper.myLoadUserByUsername("root");
        return root;
    }

    @RequestMapping(value = "/root/hello/")
    public String root(Model model){
        model.addAttribute("message", "数据库管理员【dba】/系统管理员【admin】");
        return "hello";
    }

    @RequestMapping(value = "/admin/hello/")
    public String admin(Model model){
        model.addAttribute("message", "系统管理员【admin】");
        return "hello";
    }

    @RequestMapping(value = "/song/hello/")
    public String user(Model model){
        model.addAttribute("message", "普通用户【song】");
        return "hello";
    }


}
