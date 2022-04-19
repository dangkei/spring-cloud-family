package cn.dangkei.cardservice.verification;

import java.math.BigDecimal;
import java.util.UUID;

public class VerificationApplication {
	
	private final UUID userId;
	private final BigDecimal cardCapacity;
	public VerificationApplication(UUID userId, BigDecimal cardCapacity) {
		super();
		this.userId = userId;
		this.cardCapacity = cardCapacity;
	}
	public UUID getUserId() {
		return userId;
	}
	public BigDecimal getCardCapacity() {
		return cardCapacity;
	}
	
	
}
