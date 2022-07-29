package com.fth.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.fth.pojo.Admin;
import com.fth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @author FengTianhao
 * @date 2022/7/23 10:12
 */

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest httpServletRequest)
    {
        Admin admin = adminService.login(name, pwd);
        if(admin!=null)
        {
            httpServletRequest.setAttribute("admin",admin);
            return "main";
        }
        else
            httpServletRequest.setAttribute("errmsg","用户名或者密码不正确");
            return "login";
    }
}
