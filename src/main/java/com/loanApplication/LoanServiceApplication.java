package com.loanApplication;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.loanApplication.model.Loan;
import com.loanApplication.repository.LoanRepository;

@SpringBootApplication
@EnableFeignClients
public class LoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
	}

	@Autowired
	private LoanRepository loanRepository;
  
	@PostConstruct
	public void fun() {
		Loan loan1 = new Loan("Biswajeet Mandal", "Car", "short", 875687, new Date(), new Date());
		Loan loan2 = new Loan("Deepak Roy", "Car", "short", 7887, new Date(), new Date());
		Loan loan3 = new Loan("Tanmay Patra", "Car", "short", 785687, new Date(), new Date());
		Loan loan4 = new Loan("Rohit Singh", "Car", "short", 876, new Date(), new Date());
		Loan loan5 = new Loan("Biswajeet Mandal", "Car", "short", 87685, new Date(), new Date());
		Loan loan6 = new Loan("Deepak Roy", "Car", "short", 87568, new Date(), new Date());
		Loan loan7 = new Loan("Tanmay Patra", "Car", "short", 785687, new Date(), new Date());
		Loan loan8 = new Loan("Rohit Singh", "Car", "short", 875687, new Date(), new Date());
		Loan loan9 = new Loan("Biswajeet Mandal", "Car", "short", 1000, new Date(), new Date());
		Loan loan10 = new Loan("Deepak Roy", "Car", "short", 876876, new Date(), new Date());
		Loan loan11 = new Loan("Tanmay Patra", "Car", "short", 87687, new Date(), new Date());
		Loan loan12 = new Loan("Rohit Singh", "Car", "short", 78687, new Date(), new Date());

//		loanRepository.save(loan2);
//		loanRepository.save(loan1);
//
//		loanRepository.save(loan3);
//		loanRepository.save(loan4);
//		loanRepository.save(loan5);
//		loanRepository.save(loan6);
//		loanRepository.save(loan7);
//		loanRepository.save(loan8);
//		loanRepository.save(loan9);
//		loanRepository.save(loan10);
//		loanRepository.save(loan11);
//		loanRepository.save(loan12);
	}

}

//  ORACLE USER CREATION........................

//drop user Biswajeet Mandal;
//
//create user Biswajeet Mandal identified by oracle123;
//
//grant create session, alter session, alter user, create database link,
//create MATERIALIZED view, create procedure, create public SYNONYM,
//create role, create SEQUENCE, create table, create TRIGGER, create type,
//create view, create any index, UNLIMITED TABLESPACE 
//to Biswajeet Mandal;

//
//INSERT INTO LOAN ( LOANNUMBER, MEMBERNAME, loantype,  loanterm,  amount, origindate, enddate)
//VALUES ('Arthur Woods',
//        'Donec Elementum Lorem Foundation',
//        '1-406-810-9583',
//        'eros.turpis.non@anteMaecenasmi.co.uk',
//        '1-462-765-8157');

//////////////////////////////////////////////////////
//for(Loan loan: allLoanList) {
//String string = loan.getEndDate().toString();
//String dates = string.split(" ")[0];
//String[] splitdate = dates.split("-");
//String date=((splitdate[2]+"/"+splitdate[1]+"/"+splitdate[0]).toString());
//
//DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );
//LocalDate ld = LocalDate.parse( date , f );
//
////Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(date);  
//System.out.println(ld);
////loan.setEndDate(date1);
//}

//////////////////////////////////////////////////////