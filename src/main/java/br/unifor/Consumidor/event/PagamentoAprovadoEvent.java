package br.unifor.Consumidor.event;

public class PagamentoAprovadoEvent {
    private Long pedidoId;
    private String transacaoId;

    public PagamentoAprovadoEvent() {}

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public void setTransacaoId(String transacaoId) {
        this.transacaoId = transacaoId;
    }

    // getters/setters
}