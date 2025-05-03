package br.unifor.publicador.event;

import java.time.LocalDateTime;

public class PedidoStatusEvent {
    private Long pedidoId;
    private String novoStatus;
    private LocalDateTime dataAtualizacao;

    public PedidoStatusEvent() {
    }

    public PedidoStatusEvent(Long pedidoId, String novoStatus, LocalDateTime dataAtualizacao) {
        this.pedidoId = pedidoId;
        this.novoStatus = novoStatus;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters
    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(String novoStatus) {
        this.novoStatus = novoStatus;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
