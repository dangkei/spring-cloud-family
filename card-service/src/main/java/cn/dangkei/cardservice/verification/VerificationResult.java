package cn.dangkei.cardservice.verification;

import java.util.UUID;

public class VerificationResult {

	private UUID uuid;
	public Status status;
	
	public VerificationResult(UUID uuid, Status status) {
		super();
		this.uuid = uuid;
		this.status = status;
	}

	public static VerificationResult passed(UUID uuid) {
		return new VerificationResult(uuid,Status.VERIFICATION_PASSED);
	}
	
	public static VerificationResult failed(UUID uuid) {
		return new VerificationResult(uuid,Status.VERIFICATION_FAILED);
	}

	public UUID getUuid() {
		return uuid;
	}

	public Status getStatus() {
		return status;
	}


	public enum Status {
		VERIFICATION_PASSED,
		VERIFICATION_FAILED
	}
}
