package cn.dangkei.cardservice.verification;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cn.dangkei.cardservice.application.ApplicationResult;
import reactor.core.publisher.Mono;

@Service
public class VerificationServiceClient {
	
	private final WebClient.Builder webClientBuilder;

	public VerificationServiceClient(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}

	public Mono<VerificationResult> verify(VerificationApplication verificationApplication) {
		// TODO Auto-generated method stub
		return webClientBuilder.build().get()
				.uri(uriBuilder->uriBuilder
						.scheme("http")
						.host("fraud-verifier")
						.path("/card/verify")
						.queryParam("uuid", verificationApplication.getUserId())
						.queryParam("cardCapacity", verificationApplication
								.getCardCapacity())
						.build())
				.retrieve().bodyToMono(VerificationResult.class);
	
	}

}
