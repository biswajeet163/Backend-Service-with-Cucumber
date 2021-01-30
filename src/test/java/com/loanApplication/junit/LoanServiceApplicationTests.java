package com.loanApplication.junit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.loanApplication.LoanServiceApplication;


@SpringBootTest
class LoanServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	   void main() {
		LoanServiceApplication.main(new String[] {});
	   }

}
 