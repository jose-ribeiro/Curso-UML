package com.joseribeiro.services;

import com.joseribeiro.domain.Categoria;
import com.joseribeiro.domain.Cliente;
import com.joseribeiro.domain.Produto;
import com.joseribeiro.repositories.CategoriaRepository;
import com.joseribeiro.repositories.ProdutoRepository;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Produto buscar(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    public List<Produto> listar (){
        return produtoRepository.findAll();
    }

    public Produto adicionarProduto(Integer categoriaId, Produto produto) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
        if (!categoriaOptional.isPresent()) {
            throw new RuntimeException("Categoria não encontrada");
        }
        Categoria categoria = categoriaOptional.get();

        if (produto.getCategorias() == null) {
            produto.setCategorias(new HashSet<>());
        }
        produto.getCategorias().add(categoria);

        return produtoRepository.save(produto);
    }
}
