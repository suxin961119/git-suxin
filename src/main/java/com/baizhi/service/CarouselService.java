package com.baizhi.service;


import com.baizhi.entity.Carousel;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CarouselService {
    //查询所有
    Map<String, Object> selectAll(Integer page, Integer rows);

    //添加
    String add(Carousel carousel);

    //修改
    void update(Carousel carousel);

    //删除
    void delete(String id, HttpServletRequest request);
}
