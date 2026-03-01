package bcc.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcc.delivery.model.comida;
import bcc.delivery.repository.comidaRepository;
import bcc.delivery.service.comidaService;


@RestController
@RequestMapping("/foods")
public class comidaController {

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
    @PutMapping("/update")
    public ResponseEntity<comida> atualizarComida(@RequestBody comida food){
    if (food.getId() == null) {
    return ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(comidaServ.attComida(food.getId(), food));
    }

    @DeleteMapping("/delete/{id}")
      public ResponseEntity<String> deleteComida(@PathVariable Long id){
        comidaServ.deletarComida(id);
        return ResponseEntity.ok("Comida deletada");
    }
}
