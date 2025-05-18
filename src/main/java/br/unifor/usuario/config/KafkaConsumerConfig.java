package br.unifor.usuario.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public CommonErrorHandler kafkaErrorHandler() {
        // Tenta consumir 2 vezes, espera 1 segundo entre tentativas, depois descarta
        return new DefaultErrorHandler((record, exception) -> {
            System.err.println("❌ Erro ao consumir mensagem do Kafka: " + exception.getMessage());
            System.err.println("❌ Mensagem que gerou erro será ignorada: " + record.value());
        }, new FixedBackOff(1000L, 2)); // espera 1 segundo, tenta 2 vezes
    }
}
