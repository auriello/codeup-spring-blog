package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
