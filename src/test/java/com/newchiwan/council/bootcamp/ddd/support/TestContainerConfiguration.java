package com.newchiwan.council.bootcamp.ddd.support;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestContainerConfiguration {

    @Bean
    @ServiceConnection
    public MySQLContainer<?> testContainer() {
        return (MySQLContainer<?>) new MySQLContainer<>(DockerImageName.parse("mysql:8.4.5"))
            .withEnv("TZ", "Asia/Shanghai")
            .withCommand(
                "--lower_case_table_names=1",
                "--character-set-server=utf8mb4",
                "--collation-server=utf8mb4_general_ci",
                "--max_connections=500",
                "--wait_timeout=120"
            );
    }
}
