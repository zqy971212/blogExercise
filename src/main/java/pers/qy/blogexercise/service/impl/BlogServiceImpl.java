package pers.qy.blogexercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pers.qy.blogexercise.dao.BlogDao;
import pers.qy.blogexercise.model.entity.Blog;
import pers.qy.blogexercise.service.BlogService;

import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDao blogDao;

    @Transactional
    @Override
    public void insert(String blogImage, String blogTitle, String categoryName, String message, Long userId) {
        Blog blog = new Blog();
        blog.setBlogImage(blogImage);
        blog.setBlogTitle(blogTitle);
        blog.setCategoryName(categoryName);
        blog.setMessage(message);
        blog.setUserId(userId);

        blogDao.save(blog);
    }

    @Override
    public List<Blog> selectByUserId(Long userId) {
        return blogDao.findByUserId(userId);
    }

    @Override
    public Blog selectByBlogId(Long id) {
        return blogDao.findByBlogId(id);
    }

    @Override
    @Transactional
    public void update(Long blogId, String blogImage, String blogTitle, String categoryName, String message, Long userId) {
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setBlogImage(blogImage);
        blog.setBlogTitle(blogTitle);
        blog.setMessage(message);
        blog.setCategoryName(categoryName);
        blog.setUserId(userId);
        blogDao.save(blog);

    }

    @Override
    public List<Blog> selectByAll() {
        return blogDao.findAll();
    }

    @Override
    public List<Blog> selectByCategoryName(String name) {
        return blogDao.findByCategoryName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        blogDao.deleteByBlogId(id);
    }


}
