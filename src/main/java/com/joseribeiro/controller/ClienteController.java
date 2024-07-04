package com.joseribeiro.controller;

import com.joseribeiro.domain.Cliente;
import com.joseribeiro.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteServiceservice;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cliente obj = clienteServiceservice.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listar(){
        return clienteServiceservice.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar (@Validated @RequestBody Cliente cliente){
        return clienteServiceservice.salvar(cliente);
    }

}
