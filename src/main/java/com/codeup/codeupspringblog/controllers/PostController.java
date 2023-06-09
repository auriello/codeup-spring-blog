package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, PostCategoriesRepository catDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.catDao = catDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("posts/{id}")
    public String showPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id).orElse(null));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("categories", catDao.findAll());
        model.addAttribute("post", new Post()); // Create an empty post object for the form
        return "posts/create";
    }
// is this where the error is? modelattribute & request params?
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
//        System.out.println(post.getTitle());
//        System.out.println(post.getBody());
//        System.out.println(post.getCategories());

//        List<PostCategories> categories = new ArrayList<>();

//        for (long categoryId : categoryIds) {
//            catDao.findById(categoryId).ifPresent(categories::add);
//        }

        User user = userDao.findById(1L).orElse(null);
        if (user != null) {
            post.setUser(user);
        }

//        post.setCategories(categories);
//        System.out.println(post.getCategories());
        emailService.prepareAndSend(post, "New Post created!", post.getBody());
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Post post = postsDao.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/update")
    public String updatePost(@ModelAttribute("post") Post updatedPost) {
        Post existingPost = postsDao.findById(1L).orElse(null);

        if (existingPost != null) {
//            existingPost.setTitle(updatedPost.getTitle());
//            existingPost.setBody(updatedPost.getBody());
            postsDao.save(existingPost);
        }
// the redirect keyword will send the user back to the page versus using return statement directly that will look for the file path /posts. redirect sends to the post page
        return "redirect:/posts";
    }
}


