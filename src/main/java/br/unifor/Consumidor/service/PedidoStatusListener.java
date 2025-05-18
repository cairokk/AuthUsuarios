package br.unifor.Consumidor.service;

import br.unifor.Consumidor.event.NovoPedidoEvent;
import br.unifor.usuario.service.PedidoService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PedidoStatusListener {


    @Autowired
    PedidoService pedidoService;

    @KafkaListener(topics = "pedido-criado", groupId = "cliente-service-group")
    public void ouvirNovoPedido(@Payload NovoPedidoEvent evento) {
        if (evento.getPedidoId() == null || evento.getClienteId() == null) {
            System.err.println("❌ Mensagem inválida recebida, ignorando...");
            return;  // ignora e sai
        }

        try {
            System.out.println("📥 EVENTO RECEBIDO:");
            System.out.println("ID: " + evento.getPedidoId());
            System.out.println("Cliente: " + evento.getClienteId());

            pedidoService.registrarPedido(evento.getPedidoId(), evento.getClienteId());

            System.out.println("✅ Pedido salvo com sucesso no banco local.");
        } catch (Exception e) {
            System.err.println("❌ ERRO AO PROCESSAR EVENTO:");
            e.printStackTrace();
            throw e; // permite que o ErrorHandler entre em ação

        }
    }
}

