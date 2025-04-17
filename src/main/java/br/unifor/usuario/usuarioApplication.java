package br.unifor.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class usuarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(usuarioApplication.class, args);
    }

}
