package com.MarvelDemo.demo.Repository;

import com.MarvelDemo.demo.Entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
