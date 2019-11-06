package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public Map<String, Object> selectChopterByAlbumId(Integer page, Integer rows, String albumId) {
        Chapter chapter = new Chapter();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        chapter.setAlbumId(albumId);
        List<Chapter> list = chapterDao.selectByRowBounds(chapter, rowBounds);
        int count = chapterDao.selectCount(chapter);

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("recorde", count);
        return map;
    }

    @Override
    public String add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setCreateDate(new Date());
        int n = chapterDao.insertSelective(chapter);
        if (n == 0) {
            throw new RuntimeException("添加失败!");
        }
        return chapter.getId();
    }

    @Override
    public void edit(Chapter chapter) {
        int i = chapterDao.updateByPrimaryKeySelective(chapter);
        if (i == 0) {
            throw new RuntimeException("修改章节失败");
        }
//        if("".equals(chapter.getName()) ){
//            chapter.setName(null);
//        }
//        try{
//            chapterDao.updateByPrimaryKeySelective(chapter);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("修改失败!");
//        }

    }
}
