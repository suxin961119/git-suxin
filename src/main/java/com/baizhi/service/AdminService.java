package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    //登录
    void login(Admin admin, String inputCode, HttpServletRequest request);

    //注册
    void register(Admin admin, String code, HttpServletRequest req);
}
