Feature: Pet API

        @pet1
        Scenario: Create a new pet
            Given I have a pet payload
             When I send a request to create the pet
             Then the pet is created successfully

        @pet2
        Scenario: Update an existing pet
            Given I have a pet payload
             When I send a request to update the pet
             Then the pet is updated successfully

        @pet3
        Scenario: Find pets by status
            Given I have pets with status "available"
             When I send a request to find pets by status
             Then the pets are found successfully
        
        @pet4
        Scenario: Find pets by ID
            Given I have an existing pet with id 123456
             When I send a request to find pets by ID
             Then the pet is found successfully
        
        @pet5
        Scenario: Delete an existing pet
            Given I have an existing pet with id 123456
             When I send a request to delete the pet
             Then the pet is deleted successfully


