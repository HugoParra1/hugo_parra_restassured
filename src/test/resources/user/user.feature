Feature: User API

  @user1
  Scenario Outline: Create user
    Given I have a user payload with username "<username>" and email "<email>"
    When I send a request to create the user
    Then the user is created successfully

    Examples:
      | username | email             |
      | user1    | user1@example.com |
      | user2    | user2@example.com |
      | user3    | user3@example.com |
      | user4    | user4@example.com |
  @user2
  Scenario Outline: Get user by username
    Given I have an existing user with username "<username>"
    When I send a request to get the user by username
    Then the user is retrieved successfully

    Examples:
      | username |
      | user1    |
      | user2    |
  @user3
  Scenario Outline: Update user
    Given I have an existing user with username "<username>"
    And I have an updated user payload with email "<email>"
    When I send a request to update the user
    Then the user is updated successfully

    Examples:
      | username | email                |
      | user1    | updated1@example.com |
      | user2    | updated2@example.com |
      | user3    | updated3@example.com |
      | user4    | updated4@example.com |
  @user4
  Scenario Outline: Delete user
    Given I have an existing user with username "<username>"
    When I send a request to delete the user
    Then the user is deleted successfully

    Examples:
      | username |
      | user1    |
      | user2    |
  @user5
  Scenario Outline: Login user
    Given I have credentials with username "<username>" and password "<password>"
    When I send a request to login the user
    Then the user is logged in successfully

    Examples:
      | username | password  |
      | user1    | password1 |
      | user2    | password2 |
  @user6
  Scenario: Logout user
    When I send a request to logout the user
    Then the user is logged out successfully