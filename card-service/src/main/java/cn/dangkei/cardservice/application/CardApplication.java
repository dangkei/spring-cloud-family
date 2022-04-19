package cn.dangkei.cardservice.application;

import java.math.BigDecimal;
import java.util.UUID;

import cn.dangkei.cardservice.user.User;
/*
 * 卡片申请
 * */
public class CardApplication {

	private final UUID uuid;  	//申请单号
	private final User user;	//用户
	private final BigDecimal cardCapacity;	//额度
	private ApplicationResult applicationResult;	//结果
	
	CardApplication(UUID uuid,User user,BigDecimal cardCapacity){
		this.uuid = uuid;
		this.user = user;
		if(!User.Status.OK.equals(user.getStatus())) {
			applicationResult = ApplicationResult.rejected();
		}
		this.cardCapacity =  cardCapacity;
	}

	public ApplicationResult getApplicationResult() {
		return applicationResult;
	}

	void setApplicationResult(ApplicationResult applicationResult) {
		this.applicationResult = applicationResult;
	}

	public UUID getUuid() {
		return uuid;
	}

	public User getUser() {
		return user;
	}

	public BigDecimal getCardCapacity() {
		return cardCapacity;
	}
	
	

}
