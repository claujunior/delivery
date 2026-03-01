package bcc.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("auth")
public class loginController {
    
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("login relaizado com sucesso.");
    }
}
