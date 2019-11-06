package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    //展示所有加分页
    Map<String, Object> selectAll(Integer page, Integer rows);

    //添加
    String add(Album album);

    //修改
    void update(Album album);
    //id查询

    Album selectOne(String id);


}
