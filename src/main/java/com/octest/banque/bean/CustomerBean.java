package com.octest.banque.bean;

public class CustomerBean extends BaseBean {
	
	private long userId;
	private String name;
	private String mobileNo;
	private String emailId;
	private String address;
	private long acc_No;
	
	//Génerer les constructor avec et sans parametres setters + getters

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public long getAcc_No() {
		return acc_No;
	}

	public void setAcc_No(long acc_No) {
		this.acc_No = acc_No;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return name;
	}
}
