Feature: Login Functionality for Ecommerce.

As a user of Ecommerce
I want to be able to login to Ecommerce
So that I can access my account  

Background: 
	Given I am on the login page
	
Scenario: User should be logged in successfully with valid credentials
	Given I have entered a valid userName as "standard_user" and Password as "secret_sauce"
	When I click on login button
	Then I should be logged in Succesfully
	
	
Scenario Outline: User login is denied with invalid username or password
	Given I have entered a  userName as "<userName>" and Password as "<password>"
	When I click on login button
	Then I should get an error message as "<error_message>"
	
	Examples:
		|userName	      |password	   |error_message|		
		|locked_out_user|secret_sauce	|Epic sadface: Sorry, this user has been locked out.|	
    |standard_user	|secret_sauce1|Epic sadface: Username and password do not match any user in this service|	
	
		