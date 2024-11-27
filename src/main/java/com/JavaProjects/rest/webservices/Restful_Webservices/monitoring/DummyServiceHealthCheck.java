package com.JavaProjects.rest.webservices.Restful_Webservices.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
@Controller
public class DummyServiceHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        double chance = ThreadLocalRandom.current().nextDouble();
        if (chance < 0.9) {
            return Health.down().withDetail("Dummy Service","Working OK").build();
        }
        else {
            return Health.up().withDetail("Dummy Service","Server Down").build();
        }
    }

}
