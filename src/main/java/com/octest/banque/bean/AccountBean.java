 package com.octest.banque.bean;

import java.util.Date;

public class AccountBean extends BaseBean {
	
	//numéro de compte 
	private long acc_No;
	//date d'ouverture
	private Date openDate;
	// le solde
	private double balance;
	//autorisation de decouvert
	private double overDraftLimit;
	//le type de compte : EPARGNE ou COURANT
	private String accType;
	//le taux d'intérêt 
	private double intrestRate;
	
	
	//Génerer les constructor avec et sans parametres setters + getters

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
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
