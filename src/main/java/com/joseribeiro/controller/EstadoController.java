package com.joseribeiro.controller;

import com.joseribeiro.domain.Estado;
import com.joseribeiro.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> findAll(){
        return estadoService.listar();
    }
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Estado obj = estadoService.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
}
