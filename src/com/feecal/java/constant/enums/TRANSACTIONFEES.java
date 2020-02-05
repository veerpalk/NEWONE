package com.feecal.java.constant.enums;

public enum TRANSACTIONFEES {

	FIVE_HUNDREAD(500), HUNDREAD(100), FIFTY(50), TEN(10);
	private double fees;

	private TRANSACTIONFEES(double fees) {
		// TODO Auto-generated constructor stub
		this.fees = fees;
	}

	public double getFees() {
		return fees;
	}

}
