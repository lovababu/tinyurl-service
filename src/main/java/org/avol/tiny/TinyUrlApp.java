package org.avol.tiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Spring bootstrapping class.
 *
 * Created by lovababu on 09/06/18.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {
        TinyUrlApp.class
})
public class TinyUrlApp {

    public static void main(String[] args) {
        SpringApplication.run(TinyUrlApp.class, args);
    }
}
