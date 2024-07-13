package com.joseribeiro.services;


import com.joseribeiro.domain.*;

import com.joseribeiro.repositories.*;
import com.joseribeiro.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;



    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido criarPedido(Pedido pedido) {
        // Buscar Cliente
        Optional<Cliente> clienteOptional = clienteRepository.findById(pedido.getCliente().getId());
        if (!clienteOptional.isPresent()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        Cliente cliente = clienteOptional.get();
        pedido.setCliente(cliente);

        // Buscar Endereço de Entrega
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(pedido.getEnderecoDeEntrega().getId());
        if (!enderecoOptional.isPresent()) {
            throw new RuntimeException("Endereço de entrega não encontrado");
        }
        Endereco enderecoDeEntrega = enderecoOptional.get();
        pedido.setEnderecoDeEntrega(enderecoDeEntrega);

        // Criar Pagamento
        Pagamento pagamento = pedido.getPagamento();
        if (pagamento == null) {
            throw new RuntimeException("Pagamento não pode ser nulo");
        }
        pagamento.setPedido(pedido);

        pedido.setInstante(LocalDateTime.now());

        // Adicionar Itens do Pedido

        for (ItemPedido item : pedido.getItens()) {
            Optional<Produto> produtoOptional = produtoRepository.findById(item.getProduto().getId());
            if (!produtoOptional.isPresent()) {
                throw new RuntimeException("Produto não encontrado");
            }
            Produto produto = produtoOptional.get();
            item.setProduto(produto);
            item.setPedido(pedido);
        }

        pagamentoRepository.save(pagamento);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar (){
        return pedidoRepository.findAll();
    }


 }
