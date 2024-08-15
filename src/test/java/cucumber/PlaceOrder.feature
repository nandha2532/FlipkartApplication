
@tag
Feature: Flipkart Application

Background:
	Given Launch the Flipkart Application
	When Close the popup
	Then It should navigate to the Home Page
	
 @TC001 @Regression
	Scenario: To validate Search functionality
		
		Given User enter the text in the Search field
		When Clicked on Search button
		Then It should navigate to the search result page and display the relevant details
		Then Print the First result and keep it in the Excel
		And Select Minimum and Maximum Amount
		And Select the Rating
		And Select the Offers
		And Select the Discount
		And Select Exclude Out of stock
		And Sort the result
		And Select the Battery Capacity
		Then It should display the Relevant result


  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
