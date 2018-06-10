package org.avol.tiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * Spring bootstrapping class.
 *
 * Created by lovababu on 09/06/18.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.avol.tiny"})
public class TinyUrlApp {

    public static void main(String[] args) {
        SpringApplication.run(TinyUrlApp.class, args);
    }
}
