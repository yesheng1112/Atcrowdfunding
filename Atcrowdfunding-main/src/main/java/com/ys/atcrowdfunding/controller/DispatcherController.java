package com.ys.atcrowdfunding.controller;

import com.ys.atcrowdfunding.bean.User;
import com.ys.atcrowdfunding.manager.service.UserService;
import com.ys.atcrowdfunding.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/doLogin")
    public String doLogin(String loginacct, String userpswd , String type, HttpSession session){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("loginacct",loginacct);
        paramMap.put("userpswd",userpswd);
        paramMap.put("type",type);

        User user = userService.queryUserLogin(paramMap);

        session.setAttribute(Const.LOGIN_USER,user);

        return "redirect:/main.htm";
    }
}
