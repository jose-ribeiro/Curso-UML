package com.joseribeiro.services;

import java.util.List;
import java.util.Optional;
import com.joseribeiro.domain.Categoria;
import com.joseribeiro.repositories.CategoriaRepository;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
    public List<Categoria> listar (){
        return categoriaRepository.findAll();
    }

    public Categoria salvar (Categoria categoria){
        return categoriaRepository.save(categoria);
    }
}