Feature: To create a New Loan with Loan details
  
  ## For Client Authentication LOGIN
  Scenario: client makes call to POST /auth/login to generate a valid TOKEN
    Given the UserCredential with Username "deepak" and  Password  "123" 
    When the client calls "/auth/login" with USerCredentials
    Then the client receives status code  200
    And the response contains Username "deepak"
    
    
    
  ## For Client Authentication VALIDATE
  Scenario: client makes call to GET /auth/validate to verify the TOKEN
    When the client calls "/auth/validate" with the generated TOKEN
    Then the receive status code is  200
    
  
  
  
  
  # Post Loans
  Scenario Outline: client makes call to POST /loans/addnewloan to add the New Loan Details
    Given the Loan with LoanNumber <loanNumber> and  MemberName  <memberName> and other details as well of Loan Item
    When the client calls "/loans/addnewloan" with the given details
    Then the client receives status code of 201
     And the response contains MemberName <memberName>
  
    Examples: 
      |loanNumber  | memberName           |
      |"1"         | "Biswajeet Mandal"   | 
      |"2"         | "Deepak Roy"         |
      |"3"         | "Biswajeet Mandal"   | 
      |"4"         | "Deepak Roy"         |
      |"5"         | "Biswajeet Mandal"   | 
      |"6"         | "Deepak Roy"         |
      |"7"         | "Biswajeet Mandal"   | 
      |"8"         | "Deepak Roy"         |
      
    
    
    ### Get Loans List
      Scenario: client makes call to GET /getloan to get Loan List 
    Given Get loan List using query Param of clientType as "admin_Deepak"
    When the client calls GET "/loans/getloan" with the clientType in query Param
    Then the Get client receives status code of 200
    
    ## Get Loans List By First Name
    Scenario: client makes call to GET /getloan/firstname to get Loan List based on Member First Name
    Given Get loan List by Member First Name "Deepak" 
    When the client calls GET "/loans/getloan/firstname" with the Member First Name in query Param
    Then the Get client receives status code of 200
    
    ## Get Loans List By Last Name
    Scenario: client makes call to GET /getloan/lastname to get Loan List based on Member Last Name
    Given Get loan List by Member Last Name "Roy" 
    When the client calls GET "/loans/getloan/lastname" with the Member Last Name in query Param
    Then the Get client receives status code of 200
    
    ## Get Loans List By Loan Number
    Scenario: client makes call to GET /getloan/loannumber to get Loan Item based on Loan Number
    Given Get loan Item by Loan Number "3" and the query Param of clientType as "admin_Deepak"
    When the client calls GET "/loans/getloan/loannumber" with the Loan Number in query Param
    Then the Get client receives status code of 200
    
    
    
    
    
    
    # Update a Loan
    Scenario: client makes call to PUT /loans/update to update the existing Loan Details
    Given the Loan with LoanNumber "5" and  MemberName  "testUpdateMemberName" and other detail as well of Loan Item
    When the client calls "/loans/update" with the given detail
    Then the client receive status code of 200
    And the response contains MemberName "testUpdate"
     
     
     
    
    
    ### Delete a Loan Item
    Scenario: delete the Laon Item with LoanNumber
    Given the Loan to Be Deleted with LoanNumber "2" 
    When the client calls "/loans/delete" with the LoanNumber in query Param
    Then On successful delete the client receive status code of 200
     
     
     
     
     
     
     
     
     
     
     