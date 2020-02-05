/**
 * 
 */
package com.feecal.java.beans;

import java.util.Comparator;
import java.util.Date;

import com.feecal.java.Utils.TransactionUtils;
import com.feecal.java.constant.enums.TRANSACTIONTYPE;

public class Transaction{
	
    private String externalTransactionId;
	private String transactionId;
	private String clientId;
	private String securityId;
	private Integer transactionType;
	private Date transactionDate;
	private Double marketValue;
	private boolean priority;
	private Double transactionFees;
	

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the securityId
	 */
	public String getSecurityId() {
		return securityId;
	}

	/**
	 * @param securityId the securityId to set
	 */
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	/**
	 * @return the transactionType
	 */
	public Integer getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the marketValue
	 */
	public Double getMarketValue() {
		return marketValue;
	}

	/**
	 * @param marketValue the marketValue to set
	 */
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	/**
	 * @return the priority
	 */
	public boolean isPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	/**
	 * @return the transactionFees
	 */
	public Double getTransactionFees() {
		return transactionFees;
	}

	/**
	 * @param transactionFees the transactionFees to set
	 */
	public void setTransactionFees(Double transactionFees) {
		this.transactionFees = transactionFees;
	}

	/**
	 * @return the externalTransactionId
	 */
	public String getExternalTransactionId() {
		return externalTransactionId;
	}

	/**
	 * @param externalTransactionId the externalTransactionId to set
	 */
	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(this.clientId);
		sb.append("\t            | ");
		sb.append(TRANSACTIONTYPE.getName(this.transactionType));
		sb.append("\t            | ");
		sb.append(TransactionUtils.convertDate(this.transactionDate));
		sb.append("\t            |");
		sb.append(this.priority?"HIGH\t":"NORMAL\t");
		sb.append("\t            |");
		sb.append(this.transactionFees);
		sb.append("\t            |");
		return sb.toString();
	}
	


}
