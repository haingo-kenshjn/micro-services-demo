package com.thoughtmechanix.licenses;

import com.thoughtmechanix.common.annotation.EnableSecurity;
import com.thoughtmechanix.common.feign.FeignConfiguration;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@RefreshScope
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@Configuration
@EnableSecurity
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
@ComponentScan(
        basePackages  = { "com.thoughtmechanix.licenses","com.thoughtmechanix.common" },

// Exclude @Configuration classes that should be included only in sub contexts created
// by Ribbon's SpringClientFactory.
        excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*RibbonConfiguration") })
public class Application {

    @Autowired
    private ServiceConfig serviceConfig;
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
