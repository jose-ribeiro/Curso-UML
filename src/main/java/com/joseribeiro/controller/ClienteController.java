package com.joseribeiro.controller;

import com.joseribeiro.domain.Cliente;
import com.joseribeiro.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cliente obj = clienteService.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listar(){
        return clienteService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }


        // @PutMapping("/{id}")
   // public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
     //   Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
     //   return ResponseEntity.ok(updatedCliente);
    //}


}
