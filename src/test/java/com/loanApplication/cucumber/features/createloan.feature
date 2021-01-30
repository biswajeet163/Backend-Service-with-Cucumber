Feature: To create a New Loan with Loan details
  
  Scenario Outline: client makes call to POST /loans/addnewloan to add the New Loan Details
    Given the Loan with LoanNumber <loanNumber> and  MemberName  <memberName> and other details as well of Loan Item
    When the client calls "/loans/addnewloan" with the given details
    Then the client receives status code of 201
     And the response contains MemberName <memberName>
  
    Examples: 
      |loanNumber  | memberName   |
      |"1"         | "Biswa"     	| 
      |"2"         | "Deepak"     |
      |"3"         | "Biswa"     	| 
      |"4"         | "Deepak"     |
      |"5"         | "Biswa"     	| 
      |"6"         | "Deepak"     |
      |"7"         | "Biswa"     	| 
      |"8"         | "Deepak"     |