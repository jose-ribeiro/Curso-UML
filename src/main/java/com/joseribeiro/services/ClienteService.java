package com.joseribeiro.services;

import com.joseribeiro.domain.Cliente;
import com.joseribeiro.domain.Endereco;
import com.joseribeiro.repositories.CidadeRepository;
import com.joseribeiro.repositories.ClienteRepository;
import com.joseribeiro.repositories.EnderecoRepository;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente salvar(Cliente cliente){
        var clientSalvo = clienteRepository.save(cliente);

        var listaEndereco = new ArrayList<Endereco>();

        cliente.getEnderecos().forEach(endereco -> {
            var cidadeEncontrada = cidadeRepository.findById(endereco.getCidade().getId()).get();
            endereco.setCidade(cidadeEncontrada);
            endereco.setCliente(clientSalvo);
            enderecoRepository.save(endereco);
            //listaEndereco.add(enderecoSalvo);
        });

        cliente.setEnderecos(listaEndereco);

        return clientSalvo;
    }

    public List<Cliente> listar (){
        return clienteRepository.findAll();
    }
}