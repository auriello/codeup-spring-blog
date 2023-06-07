package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Create a constructor that accepts all fields
@AllArgsConstructor
// Create an empty constructor
@NoArgsConstructor
// Create all getter methods for each field
@Getter
// Create all setter methods for each field
@Setter
@Entity
@Table(name="parks")
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 75)
    private String name;
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    // columnDefinition allows you to modify the table definition
//    private String location;
//    private State state;
    public Park(String name, State state) {
        this.name = name;
        this.state = state;
    }
}