package pets;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PetSteps {
    private String petPayload;
    private Response response;
    private String API_KEY = "special-key";
    private String petId;
    private String petStatus;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Given("I have a pet payload")
    public void i_have_a_pet_payload() {
        petPayload = "{ \"id\": 123456, \"name\": \"Doggie\", \"status\": \"available\" }";
    }

    @When("I send a request to create the pet")
    public void i_send_a_request_to_create_the_pet() {
        response = given()
                .header("Content-Type", "application/json")
                .body(petPayload)
                .when()
                .post("/pet");
    }

    @Then("the pet is created successfully")
    public void the_pet_is_created_successfully() {
        assertEquals(200, response.getStatusCode());
    }

    @When("I send a request to update the pet")
    public void iSendARequestToUpdateThePet() {
        response = given()
                .header("Content-Type", "application/json")
                .body(petPayload)
                .when()
                .put("/pet");
    }

    @Then("the pet is updated successfully")
    public void thePetIsUpdatedSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @Given("I have an existing pet with id {int}")
    public void iHaveAnExistingPetWithId(int id) {
        petId = String.valueOf(id);
    }

    @When("I send a request to delete the pet")
    public void iSendARequestToDeleteThePet() {
        response = given()
                .header("api_key", API_KEY)
                .when()
                .delete("/pet/" + petId);
    }

    @Then("the pet is deleted successfully")
    public void thePetIsDeletedSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @Given("I have pets with status {string}")
    public void iHavePetsWithStatus(String status) {
        petStatus = status;
    }

    @When("I send a request to find pets by status")
    public void iSendARequestToFindPetsByStatus() {
        response = given()
                .header("api_key", API_KEY)
                .when()
                .get("/pet/findByStatus?status=" + petStatus);
    }

    @Then("the pets are found successfully")
    public void thePetsAreFoundSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @When("I send a request to find pets by ID")
    public void iSendARequestToFindPetsByID() {
        response = given()
                .header("api_key", API_KEY)
                .when()
                .get("/pet/" + petId);
    }

    @Then("the pet is found successfully")
    public void thePetIsFoundSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }
}
