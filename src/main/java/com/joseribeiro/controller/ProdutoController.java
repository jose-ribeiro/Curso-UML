package com.joseribeiro.controller;

import com.joseribeiro.domain.Cliente;
import com.joseribeiro.domain.Produto;
import com.joseribeiro.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Produto obj = produtoService.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> listar(){
        return produtoService.listar();
    }

    @PostMapping("/categoria/{categoriaId}")
    public ResponseEntity<Produto> adicionarProduto(@PathVariable Integer categoriaId, @RequestBody Produto produto) {
        Produto novoProduto = produtoService.adicionarProduto(categoriaId, produto);
        return ResponseEntity.ok(novoProduto);
    }

}
