Feature: Use the website to find restaurants
  So that I can order food
  As a hungry customer
  I want to be able to find restaurants in my area

  Scenario Outline: Search for restaurants in an area
    Given I want food in <postcode>
    When I search for restaurants
    Then I should see some restaurants in <postcode>
    Examples:
      | postcode |
      | "SL4 2NR" |

