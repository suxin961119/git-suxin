package com.baizhi.service;


import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.StarDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Star;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private StarDao starDao;

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Album album = new Album();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> list = albumDao.selectByRowBounds(album, rowBounds);

        for (Album a : list) {
            Star star = starDao.selectByPrimaryKey(a.getStarId());
            a.setStar(star);
        }

        int count = albumDao.selectCount(album);

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);

        return map;
    }

    //添加
    @Override
    public String add(Album album) {
        album.setId(UUID.randomUUID().toString());
        album.setCount(0);
        int n = albumDao.insertSelective(album);
        if (n == 0) {
            throw new RuntimeException("添加失败!");
        }
        return album.getId();
    }

    //修改
    @Override
    public void update(Album album) {
        if ("".equals(album.getCover())) {
            album.setCover(null);
        }
        try {
            albumDao.updateByPrimaryKeySelective(album);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!");
        }
    }

    @Override
    public Album selectOne(String id) {
        Album album = albumDao.selectByPrimaryKey(id);
        return album;
    }
}
