Feature:  To retrive the Loan List with different Scenarios
  
  Scenario: client makes call to GET /getloan/firstname to get Loan List based on Member First Name
    Given Get loan List by Member First Name "Deepak" 
    When the client calls GET "/loans/getloan/firstname" with the Member First Name in query Param
    Then the Get client receives status code of 200
    
  
  
  


