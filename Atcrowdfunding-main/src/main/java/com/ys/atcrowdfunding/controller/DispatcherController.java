package com.ys.atcrowdfunding.controller;

import com.ys.atcrowdfunding.bean.User;
import com.ys.atcrowdfunding.manager.service.UserService;
import com.ys.atcrowdfunding.util.AjaxResult;
import com.ys.atcrowdfunding.util.Const;
import com.ys.atcrowdfunding.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();//销毁session对象，或清理session域。
        return "redirect:/index.htm";
    }

    //异步请求
    //@ResponseBody 结合Jackson组件，将返回结果转换为字符串，将JSON串以流的形式返回给客户端。
    @ResponseBody
    @RequestMapping(value = "/doLogin")
    public Object doLogin(String loginacct, String userpswd , String type, HttpSession session){

        AjaxResult result = new AjaxResult();

        try {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("loginacct",loginacct);
            paramMap.put("userpswd", MD5Util.digest(userpswd));
            paramMap.put("type",type);

            User user = userService.queryUserLogin(paramMap);

            session.setAttribute(Const.LOGIN_USER,user);
            result.setSuccess(true);
            //{"success":true}
        } catch (Exception e) {
            result.setMessage("登录失败！");
            e.printStackTrace();
            result.setSuccess(false);
            //{"success":false,"message":"登录失败！"}
        }

        return result;
    }

    //同步请求:
    /*@RequestMapping(value = "/doLogin")
    public String doLogin(String loginacct, String userpswd , String type, HttpSession session){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("loginacct",loginacct);
        paramMap.put("userpswd",userpswd);
        paramMap.put("type",type);

        User user = userService.queryUserLogin(paramMap);

        session.setAttribute(Const.LOGIN_USER,user);

        return "redirect:/main.htm";
    }*/
}
