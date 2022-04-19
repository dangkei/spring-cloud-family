package cn.dangkei.userservice.user;

import java.time.LocalDate;
import java.util.UUID;

public class User {
	private final UUID uuid;
	private final LocalDate dateOfBirth;
	private final String name;
	private String surname;
	private final String idNo;
	private Status status;
	
	public User(UserDto userDto) {
		this.uuid = UUID.randomUUID();
		// TODO Auto-generated constructor stub
		dateOfBirth = userDto.dateOfBirth;
		name = userDto.name;
		surname = userDto.surname;
		idNo = userDto.idNo;
	}

	
	
	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public UUID getUuid() {
		return uuid;
	}



	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}



	public String getName() {
		return name;
	}



	public String getIdNo() {
		return idNo;
	}



	public enum Status {
		NEW,
		OK,
		FRAUD
	}
}
