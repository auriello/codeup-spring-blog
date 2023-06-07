package com.codeup.codeupspringblog.repositories;
//the interface is used for all the repositories that are used in the application. this essentially is like the dao

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
