Feature: Role
  As I user I want to be able to create and update Roles and User Roles.

  Scenario: List all roles
    Given I search for the role list
    Then I should receive the role list with Project Owner, Developer and Tester

  Scenario: Create a new role
    Given I have a role service
    When I insert a new role to role service
    Then I should receive code 201 created

  Scenario: Verify if role was created after being added to service
    Given I just added a new role service
    When I search for the new role
    Then I should receive the new role

  Scenario: Assign a new user to previous created role
    Given I have a user id
    When I insert new user to role
    Then I should receive code 204 No Content

  Scenario: Verify if user was assigned to designed role
    Given I just added a new role to a user
    When I search for the role list
    Then I should receive the role description

  Scenario: Update Existing Role
    Given I update existing information on role
    Then I should receive code 204 No Content

    Scenario: Update Role to a team
      Given I provide the system with team link
      Then All users should be changed at once
