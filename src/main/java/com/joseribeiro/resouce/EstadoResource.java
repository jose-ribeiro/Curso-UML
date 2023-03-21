package com.joseribeiro.resouce;

import com.joseribeiro.domain.Estado;
import com.joseribeiro.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estado")
public class EstadoResource {
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }
}
