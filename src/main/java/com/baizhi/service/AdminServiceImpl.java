package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    //登录
    @Override
    public void login(Admin admin, String inputCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String securityCode = (String) session.getAttribute("securityCode");
        if (securityCode.equals(inputCode)) {
            Admin a = new Admin();
            a.setUsername(admin.getUsername());
            Admin loginAdmin = adminDao.selectOne(a);
            if (loginAdmin != null) {
                if (loginAdmin.getPassword().equals(admin.getPassword())) {
                    session.setAttribute("loginAdmin", loginAdmin);
                } else {
                    throw new RuntimeException("密码错误,请重新输入!");
                }
            } else {
                throw new RuntimeException("用户名错误,请重新输入!");
            }
        } else {
            throw new RuntimeException("验证码错误,请重新输入!");
        }
    }

    //注册
    @Override
    public void register(Admin admin, String code, HttpServletRequest req) {

        HttpSession session = req.getSession();
        String securityCode = (String) session.getAttribute("code");

        if (securityCode.equals(code)) {
            admin.setId(UUID.randomUUID().toString());
            int n = adminDao.insertSelective(admin);
            if (n == 0) {
                throw new RuntimeException("添加失败");
            }
        }
    }
}
