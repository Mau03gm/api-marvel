package com.MarvelDemo.demo.Service;

import com.MarvelDemo.demo.DTO.LoginDTO;
import com.MarvelDemo.demo.Entitys.User;
import com.MarvelDemo.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class authService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .roles("USER")
                .build();

    }

    public User registerUser(LoginDTO user) {
        User registerUser = new User();
        registerUser.setEmail(user.getEmail());
        registerUser.setPassword(user.getPassword());
        return userRepository.save(registerUser);
    }
}