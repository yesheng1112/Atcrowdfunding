package com.ys.atcrowdfunding.manager.controller;

import com.ys.atcrowdfunding.bean.User;
import com.ys.atcrowdfunding.manager.service.UserService;
import com.ys.atcrowdfunding.util.AjaxResult;
import com.ys.atcrowdfunding.util.Page;
import com.ys.atcrowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "user/index";
    }

    //条件查询
    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize,
                        String queryText){
        AjaxResult result = new AjaxResult();

        try {
            Map paramMap = new HashMap();
            paramMap.put("pageno",pageno);
            paramMap.put("pagesize",pagesize);

            if (StringUtil.isNotEmpty(queryText)){
                if (queryText.contains("%")){
                    queryText = queryText.replaceAll("%","\\\\%");
                }
                paramMap.put("queryText",queryText);
            }
            Page<User> page = userService.queryPage(paramMap);

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败！");
        }

        return result;//将对象序列化为JSON字符串，以流的形式返回。
    }

    //异步请求
    /*@ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize){
        AjaxResult result = new AjaxResult();

        try {
            Page<User> page = userService.queryPage(pageno,pagesize);
            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("查询数据失败！");
        }

        return result;//将对象序列化为JSON字符串，以流的形式返回。
    }*/

    //同步请求
    /*@RequestMapping("/index")
    public String index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10")Integer pagesize, Map map){

        Page<User> page = userService.queryPage(pageno,pagesize);

        map.put("page",page);

        return "user/index";
    }*/
}
