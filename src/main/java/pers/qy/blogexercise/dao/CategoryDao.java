package pers.qy.blogexercise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.qy.blogexercise.model.entity.Category;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    Category save(Category category);

    List<Category> findAll();

    Category findCategoryByCategoryId(Long id);

    List<Category> deleteByCategoryId(Long id);
}
