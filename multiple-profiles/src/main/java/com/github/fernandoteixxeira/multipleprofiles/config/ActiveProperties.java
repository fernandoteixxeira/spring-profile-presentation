package com.github.fernandoteixxeira.multipleprofiles.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "active")
public record ActiveProperties(
        Boolean company,
        Boolean person) {
}
