package br.unifor.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
        "br.unifor.usuario",
        "br.unifor.publicador",
        "br.unifor.Consumidor"
})
public class usuarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(usuarioApplication.class, args);
    }

}
