package user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.Before;


public class UserSteps {
    private String username;
    private String email;
    private String userPayload;
    private Response response;
    private String API_KEY = "special-key";
    private String password;




    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Given("I have a user payload with username {string} and email {string}")
    public void iHaveAUserPayloadWithUsernameUsernameAndEmailEmail(String un, String mail) {
        username = un;
        email = mail;
        userPayload = "{ \"id\": 0, \"username\": \"" + username + "\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"" + email + "\", \"password\": \"password\", \"phone\": \"123456789\", \"userStatus\": 0 }";

    }

    @When("I send a request to create the user")
    public void iSendARequestToCreateTheUser() {
        response = given()
                .header("Content-Type", "application/json")
                .body(userPayload)
                .when()
                .post("/user");
    }

    @Then("the user is created successfully")
    public void theUserIsCreatedSuccessfully() {
        assertEquals(200, response.getStatusCode());
    }

    @Given("I have an existing user with username {string}")
    public void iHaveAnExistingUserWithUsername(String un) {

    }

    @When("I send a request to get the user by username")
    public void iSendARequestToGetTheUserByUsername() {
    }

    @Then("the user is retrieved successfully")
    public void theUserIsRetrievedSuccessfully() {
    }

    @Given("I have an updated user payload with email {string}")
    public void i_have_an_updated_user_payload_with_email(String email) {
        this.email = email;
        userPayload = "{ \"id\": 0, \"username\": \"" + username + "\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"" + email + "\", \"password\": \"password\", \"phone\": \"123456789\", \"userStatus\": 0 }";
    }

    @When("I send a request to update the user")
    public void i_send_a_request_to_update_the_user() {
        response = given()
                .header("Content-Type", "application/json")
                .body(userPayload)
                .when()
                .put("/user/" + username);
    }

    @Then("the user is updated successfully")
    public void the_user_is_updated_successfully() {
        assertEquals(200, response.getStatusCode());
    }

    @When("I send a request to delete the user")
    public void i_send_a_request_to_delete_the_user() {
        response = given()
                .header("api_key", API_KEY)
                .when()
                .delete("/user/" + username);
    }

    @Then("the user is deleted successfully")
    public void the_user_is_deleted_successfully() {
        assertEquals(200, response.getStatusCode());
    }

    @Given("I have credentials with username {string} and password {string}")
    public void i_have_credentials_with_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @When("I send a request to login the user")
    public void i_send_a_request_to_login_the_user() {
        response = given()
                .header("Content-Type", "application/json")
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login");
    }

    @Then("the user is logged in successfully")
    public void the_user_is_logged_in_successfully() {
        assertEquals(200, response.getStatusCode());
    }

    @When("I send a request to logout the user")
    public void i_send_a_request_to_logout_the_user() {
        response = given()
                .header("api_key", API_KEY)
                .when()
                .get("/user/logout");
    }

    @Then("the user is logged out successfully")
    public void the_user_is_logged_out_successfully() {
        assertEquals(200, response.getStatusCode());
    }
}
