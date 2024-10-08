package com.joseribeiro.controller;

import com.joseribeiro.domain.Categoria;
import com.joseribeiro.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria obj = categoriaService.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> listar(){
        return categoriaService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar (@Validated @RequestBody Categoria categoria){
        return categoriaService.salvar(categoria);
    }



}