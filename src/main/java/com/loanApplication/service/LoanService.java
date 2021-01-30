package com.loanApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanApplication.exception.LoanDoesNotExistException;
import com.loanApplication.model.Loan;
import com.loanApplication.repository.LoanRepository;

@Service
public class LoanService {
	@Autowired
	private LoanRepository loanRepository;
	
	
	// All Loan for Admin
	public List<Loan> getAllLoanListAdmin() {
		return loanRepository.findAll();   
	} 
	
	 
	 

	// Loans for specific User
	public List<Loan> getAllLoanListUser(String userName) {
		List<Loan> userList = new ArrayList<Loan>();

		List<Loan> allLoans = loanRepository.findAll();
		for (Loan loan : allLoans) { 
			if (loan.getMemberName().split(" ")[0].equalsIgnoreCase(userName)) {
				userList.add(loan);
			}
		} 
		return userList;
	}
 
	
	
	
	// Loan By LoanNumber for Admin
	public List<Loan> getLoanByLoanNumberAdmin(String loannumber) {
		List<Loan> newLoan = new ArrayList<>();

		List<Loan> allLoans = loanRepository.findAll();
		for (Loan loan : allLoans) {

			if (loannumber.equals(Long.toString(loan.getLoanNumber()))) {
				newLoan.add(loan);
			}
		}
		return newLoan;
	}

	
	
	
	// Loan By LoanNumber for User
	public List<Loan> getLoanByLoanNumberUser(String userName, String loannumber) {
		List<Loan> newLoan = new ArrayList<>();

		List<Loan> allLoans = getAllLoanListUser(userName);
		for (Loan loan : allLoans) {

			if (loannumber.equals(Long.toString(loan.getLoanNumber()))) {
				newLoan.add(loan);
			}
		}
		return newLoan; 
	}
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////// FOR ADMIN ONLY

	public List<Loan> getLoanByFirstName(String firstname) {
		List<Loan> newLoan = new ArrayList<>();

		List<Loan> allLoans = loanRepository.findAll();
		for (Loan loan : allLoans) {
			String[] split = loan.getMemberName().split(" ");
			if (split[0].toLowerCase().equals(firstname.toLowerCase())) {
				newLoan.add(loan);
			}
		}
		return newLoan;
	} 
	
	
	
	

	public List<Loan> getLoanByLastName(String lastname) {
		List<Loan> newLoan = new ArrayList<>();

		List<Loan> allLoans = loanRepository.findAll();
		for (Loan loan : allLoans) {
 
			try {
				String[] split = loan.getMemberName().split(" ");
				if (split[1].toLowerCase().equals(lastname.toLowerCase())) {
					newLoan.add(loan);
				}
			} catch (Exception e) {
				System.out.println("The Name as LastName you Entered, does not have Second Name");
			}

		}
		return newLoan;
	}

	
	
	
	
//////////////////////////////////////////////////////////////////////
	public Loan addNewLoanItem(Loan loan) {
		return loanRepository.save(loan); 
	}

	public String deleteLoanItemById(String loanId) throws LoanDoesNotExistException {
		try {
			loanRepository.deleteById(Long.parseLong(loanId)); // main
			return "Deleted";
		} catch (Exception e) { 
			throw new LoanDoesNotExistException("");
		}
	} 
	  
	  
	 
	
	
 
	public Loan updateLoanItem(Loan newLoan) throws LoanDoesNotExistException {
		Optional<Loan> oldLoan = loanRepository.findById(newLoan.getLoanNumber());
		if (!oldLoan.isPresent()) {
			throw new LoanDoesNotExistException("");
		} 
		oldLoan.get().setMemberName(newLoan.getMemberName());
		oldLoan.get().setLoanType(newLoan.getLoanType());
		oldLoan.get().setLoanTerm(newLoan.getLoanTerm());
		oldLoan.get().setAmount(newLoan.getAmount());
		oldLoan.get().setOriginDate(newLoan.getOriginDate());
		oldLoan.get().setEndDate(newLoan.getEndDate());

		return loanRepository.save(oldLoan.get());
	} 

	
	
	
	
	// For Testing Purpose..............................
	public List<Loan> test() {
		return loanRepository.findAll();
	}

	// ...

}
