package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add additional query methods if needed

    User findByUsername(String username);

}
