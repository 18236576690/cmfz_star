package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.RasterOp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Article article = new Article();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> list = articleDao.selectByRowBounds(article, rowBounds);
        int count = articleDao.selectCount(article);
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",list);
        map.put("total",count%rows==0?count/rows:count/rows+1);
        map.put("records",count);
        return map;
    }

    @Override
    public void add(Article article) {
        articleDao.insert(article);
        //第一个参数：REST Host
        //第二个参数：发布消息的App Key
//        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-e891702436bc4f33b60b17739844b6b2");
        //第一个参数：channel的名称
        //第二个参数：发布的内容
//        goEasy.publish("test-channel", "www.baidu.com");
    }

    @Override
    public void edit(Article article) {

    }

    @Override
    public void del(String id) {

    }
}
