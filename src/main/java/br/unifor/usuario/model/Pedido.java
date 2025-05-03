package br.unifor.usuario.model;

import br.unifor.usuario.enums.StatusPedido;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID do pedido vindo do outro serviço

    @Column(name = "id_pedido", unique = true, nullable = false)
    private Long id_pedido;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Column(nullable = false)
    private UUID fornecedorId;  // ID do fornecedor relacionado a este pedido

    private LocalDateTime ultimaAtualizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

