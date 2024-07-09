package com.joseribeiro.controller;

import com.joseribeiro.domain.Cidade;
 import com.joseribeiro.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cidades")
public class cidadeController {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cidade obj = cidadeService.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cidade> listar(){
        return cidadeService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar (@Validated @RequestBody Cidade cidade){
        return cidadeService.salvar(cidade);
    }
}
