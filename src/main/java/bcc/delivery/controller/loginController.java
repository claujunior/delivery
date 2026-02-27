package bcc.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.delivery.repository.usuarioRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

@RequestMapping("auth")
public class loginController {
    
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("login relaizado com sucesso.");
    }
}
