package com.loanApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loanApplication.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

//	@Query("SELECT u FROM Loan u WHERE u.memberName = userName")
//	List<Loan> findByMemberName(String userName);

}
