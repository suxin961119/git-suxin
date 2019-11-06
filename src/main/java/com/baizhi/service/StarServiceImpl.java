package com.baizhi.service;

import com.baizhi.dao.StarDao;
import com.baizhi.entity.Star;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StarServiceImpl implements StarService {

    @Autowired
    private StarDao starDao;

    //展示所有数据加分页
    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Star star = new Star();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Star> list = starDao.selectByRowBounds(star, rowBounds);
        int count = starDao.selectCount(star);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    //添加
    @Override
    public String add(Star star) {
        star.setId(UUID.randomUUID().toString());
        star.setBir(new Date());
        int n = starDao.insertSelective(star);
        if (n == 0) {
            throw new RuntimeException("添加失败,请重新添加!");
        }

        return star.getId();
    }

    //修改
    @Override
    public void update(Star star) {
        if ("".equals(star.getPhoto())) {
            star.setPhoto(null);
        }
        try {
            starDao.updateByPrimaryKeySelective(star);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!");
        }
    }

    @Override
    public List<Star> getAllStarForSelect() {
        List<Star> list = starDao.selectAll();
        return list;
    }


}
