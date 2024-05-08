package com.pyramid.game.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.vault.config.EnvironmentVaultConfiguration;

/**
 * Created by Suvorov Vassilievitch
 * Date: 19/04/2024
 * Time: 01:15
 * Project Name: pyramid-game-api
 */
@Configuration
@PropertySource(value = { "vault-config.properties" })
@Import(value = EnvironmentVaultConfiguration.class)
public class VaultEnvironmentConfig {
}
