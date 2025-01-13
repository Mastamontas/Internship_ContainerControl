package com.DEVLOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application
{
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
        System.out.println("Hello world");
    }
}
