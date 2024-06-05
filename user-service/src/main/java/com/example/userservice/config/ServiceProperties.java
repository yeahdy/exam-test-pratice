package com.example.userservice.config;

import com.example.userservice.common.url.OrderUrl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {
    private OrderUrl order;
}
