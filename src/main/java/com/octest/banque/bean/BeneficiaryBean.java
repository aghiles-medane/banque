package com.octest.banque.bean;

public class BeneficiaryBean extends BaseBean {
	
	private long acc_No;
	private String bankName;
	private String  name;
	private String confirmAccNo;
	private long userId;
	
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

	public String getConfirmAccNo() {
		return confirmAccNo;
	}

	public void setConfirmAccNo(String confirmAccNo) {
		this.confirmAccNo = confirmAccNo;
	}

	public long getAcc_No() {
		return acc_No;
	}

	public void setAcc_No(long acc_No) {
		this.acc_No = acc_No;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	

}
