#Booking a room
@tag
Feature: Room Booking
	Acceptance tests for booking a room at the hotel

@tag1
Scenario: Existing customer books empty room

Given The user is an existing customer "Joe" "User"
When The user books room 123 for 5 nights
Then The user should receive message confirming booking