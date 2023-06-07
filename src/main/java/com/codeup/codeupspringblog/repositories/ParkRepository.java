package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park, Long> {
    Park findByName(String name);
}