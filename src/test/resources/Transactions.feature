Feature: Transaction Status
  Scenario: Transaction is not present in the system
	Given A transaction that is not stored in our system
	When I check the status from any channel
	Then The system returns the status 'INVALID'
	
Scenario: Transaction is stored in the system
	Given A transaction that is stored in our system
	When I check the status from CLIENT or ATM channel
	And the transaction date is before today
	Then The system returns the status 'SETTLED'
	And the amount substracting the fee

	Given A transaction that is stored in our system
	When I check the status from INTERNAL channel
	And the transaction date is before today
	Then The system returns the status 'SETTLED'
	And the amount
	And the fee	

	Given A transaction that is stored in our system
	When I check the status from CLIENT or ATM channel
	And the transaction date is equals to today
	Then The system returns the status 'PENDING'
	And the amount substracting the fee

	Given A transaction that is stored in our system
	When I check the status from INTERNAL channel
	And the transaction date is equals to today
	Then The system returns the status 'PENDING'
	And the amount
	And the fee

	Given A transaction that is stored in our system
	When I check the status from CLIENT channel
	And the transaction date is greater than today
	Then The system returns the status 'FUTURE'
	And the amount substracting the fee

	Given A transaction that is stored in our system
	When I check the status from ATM channel
	And the transaction date is greater than today
	Then The system returns the status 'PENDING'
	And the amount substracting the fee

	Given A transaction that is stored in our system
	When I check the status from INTERNAL channel
	And the transaction date is greater than today
	Then The system returns the status 'FUTURE'
	And the amount
	And the fee