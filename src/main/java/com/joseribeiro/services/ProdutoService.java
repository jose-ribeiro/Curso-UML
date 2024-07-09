package com.joseribeiro.services;

import com.joseribeiro.domain.Cliente;
import com.joseribeiro.domain.Produto;
import com.joseribeiro.repositories.ProdutoRepository;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscar(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    public List<Produto> listar (){
        return produtoRepository.findAll();
    }

    public Produto salvar (Produto produto){
        return produtoRepository.save(produto);
    }
}
