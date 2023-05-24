package pers.qy.blogexercise.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "blog_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long blogId;

    @Column(name = "blog_image")
    private String blogImage;

    @Column(name = "blog_title")
    private String blogTitle;


    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id")
    private String userId;
}
