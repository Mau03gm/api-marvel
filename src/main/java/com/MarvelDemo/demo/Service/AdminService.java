package com.MarvelDemo.demo.Service;

import com.MarvelDemo.demo.DTO.LoginDTO;
import com.MarvelDemo.demo.Entitys.Admin;
import com.MarvelDemo.demo.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository userRepositor;

    public void registerUser(LoginDTO user) {
        Admin registerUser = new Admin();
        registerUser.setEmail(user.getEmail());
        registerUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositor.save(registerUser);
    }

}
