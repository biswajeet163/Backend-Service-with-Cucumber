Feature: To delete a Loan with LoanNumber
  
	Scenario: delete the Laon Item with LoanNumber
    Given the Loan to Be Deleted with LoanNumber "1" 
    When the client calls "/loans/delete" with the LoanNumber in query Param
    Then On successful delete the client receive status code of 200
    
    
   #Scenario: client makes call to DELETE /loans/delete to delete the Loan
    #Given the Already Deleted Loan to be deleted again with LoanNumber "1" 
    #When the client calls "/loans/delete" with the LoanNumber in query Param
    #Then On delete call, user must get error as NOT FOUND 204
   

  