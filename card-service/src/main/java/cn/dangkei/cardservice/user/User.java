package cn.dangkei.cardservice.user;

import java.util.UUID;

public class User {
	private UUID uuid;
	private Status status;
	
	User(UUID uuid,Status status){
		this.uuid=uuid;
		this.status=status;
	}
	
	User(){	
	}	
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}



	public enum Status {
		NEW,
		OK,
		FRAUD
	}
}
