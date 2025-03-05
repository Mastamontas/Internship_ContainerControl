package com.DEVLOP.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TaskExecutorConfig {

    @Bean
    public TaskExecutor transactionalTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Adjust as needed
        executor.setMaxPoolSize(10); // Adjust as needed
        executor.setQueueCapacity(25); // Adjust as needed
        executor.setThreadNamePrefix("transactional-task-");
        executor.initialize();
        return executor;
    }
}
