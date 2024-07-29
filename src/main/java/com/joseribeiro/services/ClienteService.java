package com.joseribeiro.services;
import com.joseribeiro.domain.Cliente;
import com.joseribeiro.domain.Endereco;
import com.joseribeiro.repositories.ClienteRepository;
import com.joseribeiro.repositories.EnderecoRepository;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @Autowired
    private RestTemplate restTemplate;

    public Cliente buscar(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id);

        clienteRepository.delete(cliente);
    }


    @Transactional
    public Cliente salvar(Cliente cliente) {
        Set<Endereco> enderecos = new HashSet<>(cliente.getEnderecos());
        cliente.getEnderecos().clear();
        Cliente clientesalvo = clienteRepository.save(cliente);

        for (Endereco endereco : enderecos) {
            String url = "https://viacep.com.br/ws/" + endereco.getCep() + "/json/";
            Endereco enderecoFromApi = restTemplate.getForObject(url, Endereco.class);
            if (enderecoFromApi != null) {
                enderecoFromApi.setCliente(cliente);
                enderecoFromApi.setComplemento(endereco.getComplemento());
                enderecoFromApi.setNumero(endereco.getNumero());
                cliente.getEnderecos().add(enderecoFromApi);
                enderecoRepository.save(enderecoFromApi);
            }
        }


        return clienteRepository.save(cliente);
    }
}