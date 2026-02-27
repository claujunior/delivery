package bcc.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import bcc.delivery.repository.comidaRepository;
import bcc.delivery.model.*;
import bcc.delivery.service.comidaService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/foods")
public class comidaController {
    @Autowired
    comidaRepository comidaRepo;

    @Autowired
    private comidaService comidaServ;

    @GetMapping("/get")
    public ResponseEntity<List<comida>> listarComidas(){
        List<comida> comidas = comidaServ.listarComida();
        if(comidas.isEmpty()){
            return ResponseEntity.noContent().build(); 
        }  
        return ResponseEntity.ok(comidas);
    }

    @PostMapping("/save")
public ResponseEntity<comida> cadastrarComida(@RequestBody comida food) {
    comida foodSave = comidaServ.adicionarComida(food);
    return ResponseEntity.ok(foodSave);
}

}
