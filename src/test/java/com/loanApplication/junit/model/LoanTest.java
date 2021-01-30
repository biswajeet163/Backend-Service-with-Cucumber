package com.loanApplication.junit.model;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.loanApplication.model.Loan;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class) 
class LoanTest {

	@Test
	void testLoanStringStringStringLongDateDate() {
		Loan loan = new Loan("Biswa","Car","short",10, new Date(), new Date());
	}

}
  