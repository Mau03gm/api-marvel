package com.MarvelDemo.demo.Repository;

import com.MarvelDemo.demo.Entitys.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
