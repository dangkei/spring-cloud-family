package cn.dangkei.cardservice.verification;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class IgnoredServiceClient {

	private final ReactorLoadBalancerExchangeFilterFunction lbFuncion; 
	
	public IgnoredServiceClient(ReactorLoadBalancerExchangeFilterFunction lbFuncion) {
		this.lbFuncion = lbFuncion;
		// TODO Auto-generated constructor stub
	}
	
	public Mono<String> callIgnoredService(){
		return WebClient.builder()
				.filter(lbFuncion)
				.build().get()
				.uri("http://ignored/test/allowed")
				.retrieve().bodyToMono(String.class);
				
	}

}
