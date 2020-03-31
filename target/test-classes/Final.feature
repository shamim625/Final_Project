@smoketest

Feature: Automate the website

Scenario: Login to the website and add a dress to the cart
Given User go to the website
When User click on the log in button
And User enter valid email address and password
And User click on the submit button
And User verify the  website title
Then User click on the dress button
And User print all the prices values in decending order
And User select the second dress on that list
And User verify the total price with shipping
And User logout and close the browser