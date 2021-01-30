package com.loanApplication.junit.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.loanApplication.exception.InvalidExpiredTokenException;
import com.loanApplication.exception.LoanDoesNotExistException;
import com.loanApplication.feign.FeignForSecurity;
import com.loanApplication.model.AuthResponse;
import com.loanApplication.model.Loan;
import com.loanApplication.repository.LoanRepository;
import com.loanApplication.service.LoanService;

import feign.FeignException.FeignClientException;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class LoanServiceTest {

	@Mock
	LoanRepository loanRepository;

	@Mock
	FeignForSecurity feignForSecurity;

	@Mock
	Loan loan;

	@InjectMocks
	LoanService loanService;

	@Test
	void testGetAllLoanListAdmin() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short"); 
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.getAllLoanListAdmin();
		assertNotNull(loanListAdmin);
	}

	@Test
	void testGetAllLoanListUser() {

		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.getAllLoanListUser("Biswa");
		assertNotNull(loanListAdmin);

	}

	@Test
	void testGetLoanByLoanNumberAdmin() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.getLoanByLoanNumberAdmin("1");
		assertNotNull(loanListAdmin);

	}

	@Test
	void testGetLoanByLoanNumberUser() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.getLoanByLoanNumberUser("Biswa", "1");
		assertNotNull(loanListAdmin);
	}

	@Test
	void testGetLoanByFirstName() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.getLoanByFirstName("Biswa");
		assertNotNull(loanListAdmin);
	}

	@Test
	void testGetLoanByLastName() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.getLoanByLastName("Mandal");
		assertNotNull(loanListAdmin);
	}

	@Test
	void testAddNewLoanItem() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa Mandal");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		when(loanRepository.save(loans)).thenReturn(loans);
		Loan addNewLoanItem = loanService.addNewLoanItem(loans);
		assertNotNull(addNewLoanItem);
	}

	@Test
	void testDeleteLoanItemById() throws LoanDoesNotExistException {
		String loanId = "1";

		// perform the call
		loanService.deleteLoanItemById(loanId);

		// verify the mocks
		verify(loanRepository).deleteById(Long.parseLong(loanId));
	}

	@Test // ......................................................
	void testUpdateLoanItem() throws LoanDoesNotExistException {
		Loan newLoan = new Loan();
		newLoan.setLoanNumber(1);
		newLoan.setMemberName("Biswa");
		newLoan.setLoanType("Bussiness");
		newLoan.setLoanTerm("Short");
		newLoan.setAmount(1000);

		Loan oldLoan = new Loan();
		oldLoan.setMemberName(newLoan.getMemberName());
		oldLoan.setLoanType(newLoan.getLoanType());
		oldLoan.setLoanTerm(newLoan.getLoanTerm());
		oldLoan.setAmount(newLoan.getAmount());
		oldLoan.setOriginDate(newLoan.getOriginDate());
		oldLoan.setEndDate(newLoan.getEndDate());

		when(loanRepository.save(oldLoan)).thenReturn(oldLoan);
		Loan loanItem = loanService.updateLoanItem(newLoan);
		assertNotNull(loanItem);
 
	}

	@Test
	void testTest() {
		Loan loans = new Loan();
		loans.setLoanNumber(1);
		loans.setMemberName("Biswa");
		loans.setLoanType("Bussiness");
		loans.setLoanTerm("Short");
		loans.setAmount(1000);

		List<Loan> mockLoanList = new ArrayList<>();
		mockLoanList.add(loans);

		when(loanRepository.findAll()).thenReturn(mockLoanList);
		List<Loan> loanListAdmin = loanService.test();
		assertNotNull(loanListAdmin);
	}

}
