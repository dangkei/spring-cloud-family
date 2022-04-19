package cn.dangkei.demo;

import java.time.Duration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

public class DemoApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();  ///JDK8
		CircuitBreakerConfig circuitBreakerConfig  = CircuitBreakerConfig.custom()
				.failureRateThreshold(50)   //故障率超过50%断路器就开始打开
				.waitDurationInOpenState(Duration.ofMillis(1000))	//断路器打开1000毫秒后进入halfOpen状态
				.ringBufferSizeInHalfOpenState(2) 	//断路器half open状态环形缓冲区大小
				.ringBufferSizeInClosedState(2)		//断路器关闭时环形缓冲区大小
				.build();
		CircuitBreakerRegistry circuitBreakerRegistry1 = CircuitBreakerRegistry.of(circuitBreakerConfig);
		CircuitBreaker circuitBreaker2 = circuitBreakerRegistry.circuitBreaker("otherName");
		CircuitBreaker circuitBreaker  = circuitBreakerRegistry.circuitBreaker("uniqueName",circuitBreakerConfig);
		
		/*---------静态方法创建CircuitBreaker对象-----------*/
		CircuitBreaker defaultCircuitBreaker = CircuitBreaker.ofDefaults("testName");
		CircuitBreaker customCircuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
		
		/*---------使用按理-------------*/
		
	}

}
