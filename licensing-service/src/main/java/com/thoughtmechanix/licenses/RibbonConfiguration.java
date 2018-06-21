package com.thoughtmechanix.licenses;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class RibbonConfiguration {

    @Autowired
    IClientConfig ribbonClientConfig;

    // determine the server’s availability in real-time
    @Bean
    public IPing ribbonPing(IClientConfig config) {
        // external server
        // return new PingUrl();

        // delegates to Eureka to determine if a server is up
         return new NIWSDiscoveryPing();
    }

    // load balancing rule
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule();
        // return new BestAvailableRule();
        // return new RoundRobinRule();
        // return new WeightedResponseTimeRule();
    }
}
