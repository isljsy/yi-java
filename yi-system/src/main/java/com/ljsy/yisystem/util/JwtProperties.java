package com.ljsy.yisystem.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("ljsy.jwt")
public class JwtProperties {
    private String secretKey;
    private long validityInMs;

}
