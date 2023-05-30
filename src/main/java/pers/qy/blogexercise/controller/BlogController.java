package pers.qy.blogexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.qy.blogexercise.model.entity.Account;
import pers.qy.blogexercise.model.entity.Blog;
import pers.qy.blogexercise.model.entity.Category;
import pers.qy.blogexercise.service.AccountService;
import pers.qy.blogexercise.service.BlogService;
import pers.qy.blogexercise.service.CategoryService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    AccountService accountService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/blog/edit")
    public String showBlogEdit() {
        return "admin_blog_edit";
    }

    @GetMapping("/blog/all")
    public String getLoginPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userEmail = auth.getName();
        Account account = accountService.getByEmail(userEmail);

        String userName = account.getUserName();

        Long userId = account.getUserId();

        List<Blog> blogList = blogService.selectByUserId(userId);

        model.addAttribute("blogList",blogList);
        model.addAttribute("userName",userName);
        return "admin_blog_all";
    }

    @GetMapping("/blog/register")
    public String getBlogCreatePage(Model model) {
        //		現在のリクエストに紐づく Authentication を取得するには SecurityContextHolder.getContext().getAuthentication() とする。
        //		SecurityContextHolder.getContext() は、現在のリクエストに紐づく SecurityContext を返している。
        //		Authentication.getAuthorities() で、現在のログインユーザーに付与されている権限（GrantedAuthority のコレクション）を取得できる。
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //ログインした人のメールアドレスを取得
        String userEmail = auth.getName();
        //accountテーブルの中から、ユーザーのEmailで検索をかけて該当するユーザーの情報を引っ張り出す。
        Account user = accountService.getByEmail(userEmail);
        //accountテーブルの中からログインしているユーザーのIDを取得
        Long userId = user.getUserId();
        //accountテーブルの中からログインしているユーザーの名前の取得
        String userName = user.getUserName();
        //カテゴリー一覧を取得
        List<Category>categoryList = categoryService.findAll();

        //userId（管理者のId）、categoryList（カテゴリ一覧）
        //userName（管理者の名前）をmodelにセットし
        //admin_blog_register.htmlから参照可能にする。
        model.addAttribute("userId",userId);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("userName",userName);
        return "admin_blog_register";
    }

    @PostMapping("/blog/register")
    public String register(@RequestParam String blogTitle, @RequestParam("blogImage") MultipartFile blogImage, String categoryName, @RequestParam String message, @RequestParam Long userId) {

        String fileName = blogImage.getOriginalFilename();

        try {
            File blogFile = new File("./src/main/resources/static/blog-image/"+fileName);
            byte[] bytes = blogImage.getBytes();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(blogFile));
            out.write(bytes);
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        blogService.insert(null, blogTitle, categoryName, message, userId);

        return "redirect:/admin/blog/all";
    }

    @GetMapping("/blog/edit/{blogId}")
    public String getBlogDetailPage(@PathVariable Long blogId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        Account account = accountService.getByEmail(userEmail);
        String userName = account.getUserName();
        Long userId = account.getUserId();
        List<Category>categoryList = categoryService.findAll();
        Blog blogs = blogService.selectByBlogId(blogId);

        model.addAttribute("userId",userId);
        model.addAttribute("blogs",blogs);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("userName",userName);
        return "admin_blog_edit.html";
    }


    @PostMapping("/blog/update")
    public String updateData(@RequestParam Long blogId,@RequestParam String blogTitle,@RequestParam("blogImage") MultipartFile blogImage,@RequestParam String categoryName,@RequestParam String message,@RequestParam Long userId) {
        String fileName = blogImage.getOriginalFilename();
        try {
            File blogFile = new File("./src/main/resources/static/blog-image/"+fileName);
            byte[] bytes = blogImage.getBytes();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(blogFile));
            out.write(bytes);
            out.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        blogService.update(blogId, null, blogTitle,categoryName, message, userId);

        return "redirect:/admin/blog/all";
    }

    @PostMapping("/blog/delete")
    public String blogDelete(@RequestParam Long blogId) {
        blogService.deleteById(blogId);
        return "redirect:/admin/blog/all";
    }
}
