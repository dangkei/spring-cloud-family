package cn.dangkei.fraudverifier;

import java.util.UUID;

public class VerificationResult {

	private UUID userId;
	private Status status;

	public VerificationResult() {}
	
	public VerificationResult(UUID userId, Status status) {
		super();
		this.userId = userId;
		this.status = status;
	}

	public static VerificationResult passed(UUID userId) {
		return new VerificationResult(userId,Status.VERIFICATION_PASSED);	
	}
	
	public static VerificationResult failed(UUID userId) {
		return new VerificationResult(userId,Status.VERIFICATION_FAILED);	
	}
	
	
	
	public UUID getUserId() {
		return userId;
	}


	public Status getStatus() {
		return status;
	}

	enum Status {
		VERIFICATION_PASSED,
		VERIFICATION_FAILED
	}
}
