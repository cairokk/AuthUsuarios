package br.unifor.Consumidor.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class NovoPedidoEvent {
    private Long pedidoId;
    private Long clienteId;
    public NovoPedidoEvent() {
    }
    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    // Getters, Setters e Construtor vazio (obrigatório para deserialização)
}

