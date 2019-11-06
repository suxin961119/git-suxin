package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    //展示所有
    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Article article = new Article();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> list = articleDao.selectByRowBounds(article, rowBounds);
        int count = articleDao.selectCount(article);

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    //添加
    @Override
    public String add(Article article) {

        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        int n = articleDao.insertSelective(article);
        if (n == 0) {
            throw new RuntimeException("添加失败!");
        }
        return article.getId();
    }

    //修改
    @Override
    public void edit(Article article) {

        try {

            articleDao.updateByPrimaryKeySelective(article);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改失败!");
        }
    }

    //删除
    @Override
    public void del(String id) {

        Article article = articleDao.selectByPrimaryKey(id);
        int n = articleDao.deleteByPrimaryKey(id);
        if (n == 0) {
            throw new RuntimeException("删除失败!");
        }

    }
}
