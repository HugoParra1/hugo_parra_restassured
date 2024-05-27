Feature:Store API

        @store1
        Scenario:Returns pet inventories by status
             When I send a request to get pet inventories by status
             Then the pet inventories are returned successfully

        @store2
        Scenario: Create a new order for a pet
            Given I have an order payload
             When I send a request to create the order
             Then the order is created successfully
        @store3
        Scenario Outline: Get order by <orderId>
            Given I have an order ID of <orderId>
             When I send a request to get the order by ID
             Then the order is retrieved successfully

        Examples:
                  | orderId |
                  | 1       |

        @store4
        Scenario Outline: Delete order by ID
            Given I have an order ID of <orderId>
             When I send a request to delete the order by ID
             Then the order is deleted successfully
              And the order with ID of <orderId> does not exists

        Examples:
                  | orderId |
                  | 1       |


