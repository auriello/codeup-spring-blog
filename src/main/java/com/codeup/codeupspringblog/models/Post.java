package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
