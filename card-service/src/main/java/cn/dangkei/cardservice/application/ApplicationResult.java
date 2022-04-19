package cn.dangkei.cardservice.application;
/*
 * 申请结果
 * */
public class ApplicationResult {
	private Status status;
	
	public static ApplicationResult rejected() {
		return new ApplicationResult(Status.REJECTED);
	}
	
	public static ApplicationResult granted(){
		return new ApplicationResult(Status.GRANTED);
	}
	
	public ApplicationResult(Status status) {
		this.status = status;
	}
	
	public ApplicationResult() {}
	
	public Status getStatus() {
		return status;
	}
	public enum Status{
		GRANTED,
		REJECTED
	}

}
