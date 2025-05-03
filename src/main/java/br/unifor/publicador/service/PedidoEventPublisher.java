package br.unifor.publicador.service;

import br.unifor.publicador.event.PedidoStatusEvent;
import br.unifor.usuario.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoEventPublisher {
    @Autowired
    private KafkaTemplate<String, PedidoStatusEvent> kafkaTemplate;

    private static final String TOPICO = "pedido-status-atualizado";

    public void publicarAtualizacao(Pedido pedido) {

        PedidoStatusEvent event = new PedidoStatusEvent();
        event.setPedidoId(pedido.getId());
        event.setNovoStatus(pedido.getStatus().name());
        event.setDataAtualizacao(pedido.getUltimaAtualizacao());

        kafkaTemplate.send(TOPICO, event);
    }
}

