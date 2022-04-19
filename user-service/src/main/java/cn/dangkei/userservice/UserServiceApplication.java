package cn.dangkei.userservice;

import java.time.Duration;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.micrometer.tagged.TaggedCircuitBreakerMetrics;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
public class UserServiceApplication {
	
	private static final Log LOG =  LogFactory.getLog(UserServiceApplication.class);
	
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = SpringApplication.run(UserServiceApplication.class, args);
		displayAllBeans();
	}

	public static void displayAllBeans(String... args) throws Exception {
		// TODO Auto-generated method stub
		String[] beans  = applicationContext.getBeanDefinitionNames();
		
		for(String name : beans) {
			LOG.info("bean name>>>>>>>"+ name);
		}
	}

	@Bean
	RestTemplateBuilder restemplateBuilder() {
		return new RestTemplateBuilder();
	}
	
	@Bean
	Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(CircuitBreakerRegistry circuitBreakerRegistry,
			MeterRegistry meterRegistry){
		return factory->{
			factory.configureDefault(id->new Resilience4JConfigBuilder(id)
					.timeLimiterConfig(TimeLimiterConfig.custom()
							.timeoutDuration(Duration.ofSeconds(2))
							.build())
					.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
					.build());
			//for metrics
			factory.configureCircuitBreakerRegistry(circuitBreakerRegistry);
			//we need to allow adding those customizers regardless of the id
			factory.addCircuitBreakerCustomizer(circuitBreaker -> TaggedCircuitBreakerMetrics.
					ofCircuitBreakerRegistry(circuitBreakerRegistry)
					.bindTo(meterRegistry), "verifyNewUser");
		};
	}
	
	@Bean
	CircuitBreakerRegistry resilience4JCircuitBreakerRegistry() {
		return CircuitBreakerRegistry.ofDefaults();
	}
	
}
