package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //id查询加分页
    Map<String, Object> selectChopterByAlbumId(Integer page, Integer rows, String albumId);

    //添加
    String add(Chapter chapter);

    //修改
    void edit(Chapter chapter);
}
