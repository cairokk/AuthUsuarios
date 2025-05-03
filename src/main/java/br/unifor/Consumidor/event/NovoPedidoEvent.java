package br.unifor.Consumidor.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class NovoPedidoEvent {
    private Long pedidoId;
    private UUID clienteId;
    public NovoPedidoEvent() {
    }
    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    // Getters, Setters e Construtor vazio (obrigatório para deserialização)
}

