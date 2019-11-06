package com.baizhi.service;

import com.baizhi.entity.Star;

import java.util.List;
import java.util.Map;

public interface StarService {
    //展示所有加分页
    Map<String, Object> selectAll(Integer page, Integer rows);

    //添加
    String add(Star star);

    //修改
    void update(Star star);

    List<Star> getAllStarForSelect();
}
