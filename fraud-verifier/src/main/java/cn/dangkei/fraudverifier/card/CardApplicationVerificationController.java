package cn.dangkei.fraudverifier.card;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.dangkei.fraudverifier.VerificationResult;

@RestController
@RequestMapping("/cards")
public class CardApplicationVerificationController {
	private final CardApplicationVerificationService cardApplicationVerificationService;

	public CardApplicationVerificationController(
			CardApplicationVerificationService cardApplicationVerificationService) {
		this.cardApplicationVerificationService = cardApplicationVerificationService;
	}
	
	@GetMapping("/verify")
	ResponseEntity<VerificationResult> verify(@RequestParam UUID uuid,
			@RequestParam BigDecimal cardCapacity){
		VerificationResult result = cardApplicationVerificationService.verify(uuid, cardCapacity);
		
		return ResponseEntity.ok(result);
		
	}
	
}
