package com.octest.banque.bean;

import java.util.Date;

/**
 * Account JavaBean encapsulates TimeTable attributes
 * 
 * 
 */
public class AccountBean extends BaseBean {

	/**
	 * Account No of Account
	 */
	private long acc_No;
	/**
	 * Open Date of Account
	 */
	private Date openDate;
	/**
	 * Balance of Account
	 */
	private double balance;
	/**
	 * Over Draft Limit of Account
	 */
	private double overDraftLimit;
	/**
	 * Account Type of Account
	 */
	private String accType;
	/**
	 * Intrest Rate of Account
	 */
	private double intrestRate;
	
	
	
	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public double getIntrestRate() {
		return intrestRate;
	}

	public void setIntrestRate(double intrestRate) {
		this.intrestRate = intrestRate;
	}

	public long getAcc_No() {
		return acc_No;
	}

	public void setAcc_No(long acc_No) {
		this.acc_No = acc_No;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}
	
	

	public String getKey() {
		return String.valueOf(id);
	}

	public String getValue() {
		return String.valueOf(acc_No);
	}

}
