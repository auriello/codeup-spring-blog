package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.PostCategories;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostCategoriesRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final PostCategoriesRepository catDao;

    public PostController(PostRepository postsDao, UserRepository userDao, PostCategoriesRepository catDao) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.catDao = catDao;
    }

    @GetMapping
    public String viewPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String showPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postsDao.findById(id).orElse(null));
        return "posts/show";
    }

    @GetMapping("/create")
    public String showPostForm(Model model) {
        model.addAttribute("categories", catDao.findAll());
        model.addAttribute("post", new Post()); // Create an empty post object for the form
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("post") Post post, @RequestParam(name = "category") List<Long> categoryIds) {
        List<PostCategories> categories = new ArrayList<>();

        for (long categoryId : categoryIds) {
            catDao.findById(categoryId).ifPresent(categories::add);
        }

        User user = userDao.findById(1L).orElse(null);
        if (user != null) {
            post.setUser(user);
        }

        post.setCategories(categories);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Post post = postsDao.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable("id") long id, @ModelAttribute("post") Post updatedPost) {
        Post existingPost = postsDao.findById(id).orElse(null);

        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setBody(updatedPost.getBody());
            postsDao.save(existingPost);
        }

        return "redirect:/posts/" + id;
    }
}


