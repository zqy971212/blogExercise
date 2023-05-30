package pers.qy.blogexercise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.qy.blogexercise.model.entity.Blog;

import java.util.List;

@Repository
public interface BlogDao extends JpaRepository<Blog, Long> {
    Blog save(Blog blog);

    Blog findByBlogId(Long id);

    @Query(value = "SELECT b.blog_id, b.blog_image, b.blog_title, b.category_name, b.message, b.user_id " +
            "FROM blog b INNER JOIN account a ON a.user_id = b.user_id " +
            "WHERE b.user_id = :userId", nativeQuery = true)
    List<Blog> findByUserId(@Param("userId") Long userId);


    List<Blog> findByCategoryName(String categoryName);

    List<Blog> findAll();

    void deleteByBlogId(Long id);


}
