package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;


    //展示所有
    @RequestMapping("selectAll")
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String, Object> map = carouselService.selectAll(page, rows);
        return map;
    }

    //添加，修改，删除
    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Carousel carousel, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //执行添加方法
            if ("add".equals(oper)) {
                String id = carouselService.add(carousel);
                map.put("message", id);
            }
            //执行修改方法
            if ("edit".equals(oper)) {
                carouselService.update(carousel);
            }
            //执行删除方法
            if ("del".equals(oper)) {
                carouselService.delete(carousel.getId(), request);
            }
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }


    //文件上传
    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile cover, String id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //文件上传
            cover.transferTo(new File(request.getServletContext().getRealPath("/img/"), cover.getOriginalFilename()));
            //修改carousel对象中cover属性
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setCover(cover.getOriginalFilename());
            carouselService.update(carousel);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        return map;
    }

}
