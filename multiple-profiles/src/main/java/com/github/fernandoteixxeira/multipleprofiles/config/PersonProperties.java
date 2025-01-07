package com.github.fernandoteixxeira.multipleprofiles.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "person")
public record PersonProperties(
    Boolean active,
    String name,
    String nationality,
    String company) {
}
