package br.unifor.usuario.service;

import br.unifor.publicador.service.PedidoEventPublisher;
import br.unifor.usuario.DTO.PedidoDTO;
import br.unifor.usuario.enums.StatusPedido;
import br.unifor.usuario.model.Pedido;
import br.unifor.usuario.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoEventPublisher publisher;

    public void registrarPedido(Long pedidoId, Long fornecedorId) {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(pedidoId);
        pedido.setFornecedorId(fornecedorId);
        pedido.setStatus(StatusPedido.CRIADO);
        pedido.setUltimaAtualizacao(LocalDateTime.now());
        pedidoRepository.save(pedido);
    }

    public void atualizarStatusPedido( Long pedidoId, String status ) throws Exception {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

        if (pedido == null){
            throw new Exception("pedido não encontrado, id do pedido: " + pedidoId.toString());
        }

        if ("PAGO".equalsIgnoreCase(status)) {
            pedido.setStatus(StatusPedido.PAGO);
        } else {
            pedido.setStatus(StatusPedido.CANCELADO);
        }

        pedidoRepository.save(pedido);

    }

    public void atualizarStatus(Long pedidoId, StatusPedido novoStatus, Long fornecedorId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (!pedido.getFornecedorId().equals(fornecedorId)) {
            throw new AccessDeniedException("Fornecedor não autorizado a modificar este pedido.");
        }

        pedido.setStatus(novoStatus);
        pedido.setUltimaAtualizacao(LocalDateTime.now());
        pedidoRepository.save(pedido);

        publisher.publicarAtualizacao(pedido);
    }

    public List<PedidoDTO> listarPedidosDoFornecedor(Long fornecedorId) {
        List<Pedido> pedidos = pedidoRepository.findByFornecedorId(fornecedorId);
        return pedidos.stream()
                .map(PedidoDTO::new) // usa o construtor que recebe Pedido
                .collect(Collectors.toList());
    }
}

