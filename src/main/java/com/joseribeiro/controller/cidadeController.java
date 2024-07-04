package com.joseribeiro.controller;

import com.joseribeiro.domain.Cidade;
import com.joseribeiro.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "cidades")
public class cidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> findaAll(){
        return cidadeRepository.findAll();
    }
}
