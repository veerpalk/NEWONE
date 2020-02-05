package com.feecal.java;

import static java.util.Comparator.comparing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.feecal.java.Utils.FileUtils;
import com.feecal.java.Utils.TransactionUtils;
import com.feecal.java.beans.Transaction;
import com.feecal.java.constant.FeesCalculatorConstants;
import com.feecal.java.constant.enums.TRANSACTIONTYPE;

public class FeeCalApp {

	public static List<Transaction> transcationList;
	private static final String HEADERS = "Client ID,TransactionType,Transaction Date,Priority,Processing Fee";

	public static void main(String args[]) {

		Stream<String> transactionStream = FileUtils.getStreamDataFromFilePath(FeesCalculatorConstants.fileName);
		transcationList = TransactionUtils.getTransaction(transactionStream);
		showReport(transcationList);

	}

	public static void showReport(List<Transaction> transcationList) {
		transcationList.sort(comparing(Transaction::getClientId).thenComparing(Transaction::getTransactionType)
				.thenComparing(Transaction::getTransactionDate).thenComparing(Transaction::isPriority));

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("Sample output.csv");
			fileWriter.append(HEADERS.toString());
			fileWriter.append("\n");
			for (Transaction its : transcationList) {
				try {
					fileWriter.append(String.valueOf(its.getClientId()));
					fileWriter.append(",");
					fileWriter.append(TRANSACTIONTYPE.getName(its.getTransactionType()));
					fileWriter.append(",");
					fileWriter.append(its.getTransactionDate().toString());
					fileWriter.append(",");
					fileWriter.append(its.isPriority() ? "HIGH" : "NORMAL");
					fileWriter.append(",");
					fileWriter.append(String.valueOf(its.getTransactionFees()));
					fileWriter.append("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Calculated Fees :");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"Client ID           | TransactionType       | Transaction Date                              |Priority                       | Processing Fee        |");
		transcationList.forEach(System.out::println);
	}

}
