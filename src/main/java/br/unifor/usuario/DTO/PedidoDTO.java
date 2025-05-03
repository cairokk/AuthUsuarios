package br.unifor.usuario.DTO;

import br.unifor.usuario.enums.StatusPedido;
import br.unifor.usuario.model.Pedido;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoDTO {

    private Long id;  // ID do pedido vindo do outro serviço

    private Long id_pedido;
    private StatusPedido status;
    private UUID fornecedorId;  // ID do fornecedor relacionado a este pedido
    private LocalDateTime ultimaAtualizacao;


    // Construtor a partir do Pedido
    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.status = pedido.getStatus();
        this.ultimaAtualizacao = pedido.getUltimaAtualizacao();
    }
    public Long getId() {
        return id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public UUID getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(UUID fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }
}
