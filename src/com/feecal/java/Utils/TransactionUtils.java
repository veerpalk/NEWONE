package com.feecal.java.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.feecal.java.beans.Transaction;
import com.feecal.java.constant.enums.TRANSACTIONFEES;
import com.feecal.java.constant.enums.TRANSACTIONTYPE;

public class TransactionUtils {
	private static List<Transaction> list = new ArrayList<Transaction>();
	private static int count=0;
	
	public static List<Transaction> getTransaction(Stream<String> transactionStream){

		return transactionStream.map(csvToPojo).map(it -> calculateTransactionFee(it)).collect(Collectors.toList());
	}
	
	private static Function<String, Transaction> csvToPojo = (line) -> {
		String[] record = line.split(",");
		Transaction tx = new Transaction();
		tx.setExternalTransactionId(record[0]);
		tx.setClientId(record[1]);
		tx.setSecurityId(record[2]);
		tx.setTransactionType(getTransactionType(record[3]));
		tx.setTransactionDate(convertDate(record[4]));
		tx.setMarketValue(Double.valueOf(record[5]));
		tx.setPriority(getPriorty(record[6]));
		return tx;	
	};

	private static Transaction calculateTransactionFee(Transaction transaction) {
		if (isIntraDayTransaction(transaction)) {
			transaction.setTransactionFees(TRANSACTIONFEES.TEN.getFees());
		} else {

			if (transaction.isPriority()) {
				transaction.setTransactionFees(TRANSACTIONFEES.FIVE_HUNDREAD.getFees());

			} else {
				if (transaction.getTransactionType() == TRANSACTIONTYPE.SELL.getType()
						|| transaction.getTransactionType() == TRANSACTIONTYPE.WITHDRAW.getType()) {
					transaction.setTransactionFees(TRANSACTIONFEES.HUNDREAD.getFees());

				} else if (transaction.getTransactionType() == TRANSACTIONTYPE.BUY.getType()
						|| transaction.getTransactionType() == TRANSACTIONTYPE.DEPOSIT.getType()) {
					transaction.setTransactionFees(TRANSACTIONFEES.FIFTY.getFees());

				}
			}
		}
		return transaction;
	}

	private static  boolean isIntraDayTransaction(Transaction transaction) {
		boolean isIntraDayTransaction = false;
		Transaction temp = null;
		if (list.size() > 0) {
			for (Transaction trans : list) {
				if (trans.getClientId().equals(transaction.getClientId())
						&& trans.getSecurityId().equals(transaction.getSecurityId())
						&& trans.getTransactionDate().equals(transaction.getTransactionDate())) {
					if (trans.getTransactionType() == TRANSACTIONTYPE.BUY.getType()
							&& trans.getTransactionType() == TRANSACTIONTYPE.SELL.getType()
							|| (trans.getTransactionType() == TRANSACTIONTYPE.BUY.getType()
									&& trans.getTransactionType() == TRANSACTIONTYPE.SELL.getType())) {
						isIntraDayTransaction = true;
						temp = trans;
						break;
					}
				}
			}
			if (temp != null) {
				list.remove(temp);
				temp.setTransactionFees(TRANSACTIONFEES.TEN.getFees());
				list.add(temp);
			}
		} else {
			isIntraDayTransaction = false;
		}
		return isIntraDayTransaction;
	}

	public static Boolean getPriorty(String priorty) {
		if (priorty != null) {
			priorty = priorty.trim();
			if (priorty.equalsIgnoreCase("Y")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static Integer getTransactionType(String type) {
		if (type.equals("BUY")) {
			return TRANSACTIONTYPE.BUY.getType();
		}
		if (type.equals("SELL")) {
			return TRANSACTIONTYPE.SELL.getType();
		}
		if (type.equals("DEPOSIT")) {
			return TRANSACTIONTYPE.DEPOSIT.getType();
		}
		if (type.equals("WITHDRAW")) {
			return TRANSACTIONTYPE.WITHDRAW.getType();
		}
		return null;
	}
 
	
	public static Date convertDate(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			Date currentDate = df.parse(date);
			return currentDate;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	public static Date convertDate(Date date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			String currentDate = df.format(date);
			Date formatDate=df.parse(currentDate);
			return formatDate;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

}
