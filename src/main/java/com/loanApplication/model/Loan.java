package com.loanApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@NoArgsConstructor
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long loanNumber;
	private String memberName;
	private String loanType;
	private String loanTerm;  
	private long amount;
	private Date originDate;
	private Date endDate;

	public Loan(String memberName, String loanType, String loanTerm, long amount, Date originDate, Date endDate) {
	
		this.memberName = memberName;
		this.loanType = loanType;
		this.loanTerm = loanTerm;
		this.amount = amount;
		this.originDate = originDate;
		this.endDate = endDate;
	}

	
}
 