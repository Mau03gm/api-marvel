package com.MarvelDemo.demo.Controller;

import com.MarvelDemo.demo.Config.Application;
import com.MarvelDemo.demo.Config.JwtUtil;
import com.MarvelDemo.demo.DTO.LoginDTO;
import com.MarvelDemo.demo.Service.AdminService;
import com.MarvelDemo.demo.Service.authService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Application.API_BASE_PATH +"/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private authService authService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws Exception {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authentication;
        try {
            authentication = this.authenticationManager.authenticate(login);
        } catch (Exception e) {
            throw new Exception("Username/Contraseña incorrectos");
        }
        String token;
        try {
            token = this.jwUtil.createToken(loginDTO.getEmail());
        }catch (Exception e){
            throw new Exception("Error creating token");
        }

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(authentication.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<LoginDTO> registerUser(@RequestBody LoginDTO userDTO) throws Exception{
        adminService.registerUser(userDTO);
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        String token = this.jwUtil.createToken(userDTO.getEmail());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(userDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "").build();
    }

}
