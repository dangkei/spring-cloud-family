package cn.dangkei.cardservice.application;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * 用户提交数据类前端通过cardAppliation.json格式提交给系统后对应的java类
 * */
public class CardApplicationDto {

	public User user;
	public BigDecimal cardCapacity;
	
	public static class User{
		public String name;
		public String surname;
		public String idNo;
		public LocalDate dateOfBirth;		
	}
}
