package com.baizhi.service;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselDao carouselDao;

    //展示所有加分页
    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Carousel carousel = new Carousel();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Carousel> list = carouselDao.selectByRowBounds(carousel, rowBounds);
        int count = carouselDao.selectCount(carousel);

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    //添加
    @Override
    public String add(Carousel carousel) {
        carousel.setId(UUID.randomUUID().toString());
        carousel.setDate(new Date());
        int n = carouselDao.insertSelective(carousel);
        if (n == 0) {
            throw new RuntimeException("添加失败,请重新添加!");
        }
        return carousel.getId();
    }

    //修改
    @Override
    public void update(Carousel carousel) {
        if ("".equals(carousel.getCover())) {
            carousel.setCover(null);
        }
        try {
            carouselDao.updateByPrimaryKeySelective(carousel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败,请重新修改!");
        }
    }

    //删除
    @Override
    public void delete(String id, HttpServletRequest request) {
        Carousel carousel = carouselDao.selectByPrimaryKey(id);
        int n = carouselDao.deleteByPrimaryKey(id);
        if (n == 0) {
            throw new RuntimeException("删除失败,请重新删除");
        } else {
            String cover = carousel.getCover();
            File file = new File(request.getServletContext().getRealPath("/img/"), cover);
            boolean b = file.delete();
            if (b == false) {
                throw new RuntimeException("删除图片错误!");
            }
        }
    }
}
