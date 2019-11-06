package com.baizhi.controller;

import com.baizhi.aliyun.SendMessage;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    //登录
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Admin admin, String inputCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            adminService.login(admin, inputCode, request);
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    //注册
    @RequestMapping("register")

    public String register(Admin admin, String code, HttpServletRequest req) {
        try {

            adminService.register(admin, code, req);

            return "redirect:/login/login.jsp";
        } catch (Exception e) {
            return "redirect:/login/register.jsp";
        }

    }


    @RequestMapping("exit")
    public String Exit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login/login.jsp";
    }


    //短信验证
    @RequestMapping("code")
    @ResponseBody
    public void activeAdmin(String code, HttpServletRequest req) {

        try {
            System.out.println("开始发送短信");

            SendMessage.sendMessage("666666", req);

            System.out.println("短信发送成功");
        } catch (Exception e) {
            System.out.println("短信发送失败");
            e.printStackTrace();
        }
    }

}
