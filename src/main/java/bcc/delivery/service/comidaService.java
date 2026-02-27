package bcc.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import bcc.delivery.model.comida;
import bcc.delivery.repository.comidaRepository;

@Service
public class comidaService {
    @Autowired
    comidaRepository comidaRepo;

    public comida adicionarComida(comida food){
        if (comidaRepo.findByTitle(food.getTitle()).isPresent()) {
         throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma comida com esse nome");
    }
    return comidaRepo.save(food);
    }

    public List<comida> listarComida(){
        return comidaRepo.findAll().stream().collect(Collectors.toList());
    } 
    
    public comida buscarNomeComida(String nome){
          return comidaRepo.findByTitle(nome)
            .orElseThrow(() -> new RuntimeException("Não existe comida com esse nome"));
    }

}
