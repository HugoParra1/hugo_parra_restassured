package store;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StoreSteps {
    private Response response;
    private String orderPayload;
    private String orderId;
    private String API_KEY = "special-key";

    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @When("I send a request to get pet inventories by status")
    public void i_send_a_request_to_get_pet_inventories_by_status() {
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/store/inventory");
    }

    @Then("the pet inventories are returned successfully")
    public void the_pet_inventories_are_returned_successfully() {
        assertEquals(200, response.getStatusCode());
        assertFalse(response.jsonPath().getMap("").isEmpty());
    }

    @Given("I have an order payload")
    public void iHaveAnOrderPayload() {
        orderPayload = "{\"id\": 1, \"petId\": 45172571, \"quantity\": 1, \"shipDate\": \"1975-07-17T06:24:48.658Z\", \"status\": \"approved\", \"requestId\": \"laboris qui ea in cupidatat\"}";
    }

    @When("I send a request to create the order")
    public void iSendARequestToCreateTheOrder() {
        response = given()
                .header("Content-Type", "application/json")
                .body(orderPayload)
                .when()
                .post("/store/order");
    }

    @Then("the order is created successfully")
    public void theOrderIsCreatedSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @Given("I have an order ID of {int}")
    public void iHaveAnOrderIDOfOrderId(int ID) {
        orderId = String.valueOf(ID);
    }

    @When("I send a request to get the order by ID")
    public void iSendARequestToGetTheOrderByID() {
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("store/order/" + orderId);

    }

    @Then("the order is retrieved successfully")
    public void theOrderIsRetrievedSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @When("I send a request to delete the order by ID")
    public void iSendARequestToDeleteTheOrderByID() {
        response = given()
                .header("api_key", API_KEY)
                .when()
                .delete("/store/order/" + orderId);
    }

    @Then("the order is deleted successfully")
    public void theOrderIsDeletedSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @And("the order with ID of {int} does not exists")
    public void theOrderWithIDOfOrderIdDoesNotExists(int ID) {
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("store/order/" + orderId);
        assertEquals(404, response.getStatusCode());
    }
}
