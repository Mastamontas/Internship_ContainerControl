package com.DEVLOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*
todo general program
verify code and clean
create factories to create entities for tests
verify transactional scopes from services
 */
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class MainApplication
{
    public static void main( String[] args ) {
        SpringApplication.run(MainApplication.class, args);
    }
}
