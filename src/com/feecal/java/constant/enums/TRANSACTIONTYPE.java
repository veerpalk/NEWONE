/**
 * 
 */
package com.feecal.java.constant.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Anand
 *
 */
public enum TRANSACTIONTYPE {

	BUY("BUY", 1), SELL("SELL", 2), DEPOSIT("DEPOSITE", 3), WITHDRAW("WITHDRAW", 4);

	private int type;
	private String name;

	private TRANSACTIONTYPE(String name, int type) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.type = type;
	}

	public static String getName(int type) {
		Optional<TRANSACTIONTYPE> types=Arrays.asList(TRANSACTIONTYPE.values()).stream().filter(it->it.getType()==type).findFirst();
		return types.get().getName();
	}
	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

}
