package com.pyramid.game.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/04/2024
 * Time: 20:25
 * Project Name: pyramid-game-api
 */

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.main")
    HikariDataSource hikariDataSource() {

        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();

    }
}
