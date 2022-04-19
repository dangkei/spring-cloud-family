package cn.dangkei.userservice.user;

import java.util.UUID;

public class VerificationResult {
	
	private UUID userId;
	private Status status;

	public VerificationResult() {
		// TODO Auto-generated constructor stub
	}
	private VerificationResult(UUID userId, Status status) {
		super();
		this.userId = userId;
		this.status = status;
	}
	
	public static VerificationResult passed(UUID userId) {
		return new VerificationResult(userId, Status.VERIFICATION_PASSED);
	}

	public static VerificationResult failed(UUID userId) {
		return new VerificationResult(userId, Status.VERIFICATION_FAILED);
	}

	public enum Status {
		VERIFICATION_PASSED,
		VERIFICATION_FAILED
	}
}
