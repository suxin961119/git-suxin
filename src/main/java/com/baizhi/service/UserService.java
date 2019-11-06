package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //展示所有加分页
    Map<String, Object> selectUsersByStarId(Integer page, Integer rows, String starId);

    List<User> export();


}
