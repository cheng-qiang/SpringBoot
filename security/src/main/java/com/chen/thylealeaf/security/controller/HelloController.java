package com.chen.thylealeaf.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 程强
 * @date 2020年04月23日 14:31
 * @Description:
 */
@Controller
@RequestMapping("/root/")
public class HelloController {

    @RequestMapping("/toHello")
    public String toHello(){
        return "hello";
    }

}
