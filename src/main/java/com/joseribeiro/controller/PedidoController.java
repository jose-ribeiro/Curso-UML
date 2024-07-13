package com.joseribeiro.controller;

import com.joseribeiro.domain.Pedido;
import com.joseribeiro.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = pedidoService.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> listar(){
        return pedidoService.listar();
    }
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }

}
