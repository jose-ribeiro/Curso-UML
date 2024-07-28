package com.joseribeiro.services;

import com.joseribeiro.domain.Cidade;
import com.joseribeiro.domain.Cliente;
import com.joseribeiro.repositories.CidadeRepository;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listar (){
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Integer id) {
        Optional<Cidade> obj = cidadeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cidade salvar (Cidade cidade){
        return cidadeRepository.save(cidade);
    }



}
