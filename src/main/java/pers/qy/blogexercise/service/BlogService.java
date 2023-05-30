package pers.qy.blogexercise.service;

import pers.qy.blogexercise.model.entity.Blog;

import java.util.List;

public interface BlogService {
    void insert (String blogImage, String blogTitle, String categoryName,
                 String message, Long userId);

    List<Blog> selectByUserId (Long userId);

    Blog selectByBlogId(Long id);

    void update(Long blogId, String blogImage, String blogTitle, String categoryName,
                String message, Long userId);

    List<Blog> selectByAll();

    List<Blog> selectByCategoryName(String name);

    void deleteById(Long id);
}
