package restfullbooker;

import http.manager.v1.HttpManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class AuthStepDefinitions {
    private final HttpManager httpManager = new HttpManager();
    private Response response;
    private String requestBody;

    @Given("request body")
    public void setRequestBody(String body){
        requestBody = body;
    }

    @And("header content-type {string}")
    public void setContentType(String contentType){
        httpManager.setContentType(contentType);
    }

    @When("do POST request to {string}")
    public void sendPostRequest(String uri){
        response = httpManager.sendPost(uri, requestBody);
    }

    @Then("status code is {int}")
    public void checkStatusCode(Integer statusCode){
        Assertions.assertEquals(statusCode, response.statusCode(), "there is difference in status code. Check response!");
    }

    @And("response contains token")
    public void checkToken(){
        String token = response.then().extract().jsonPath().get("token");
    }

}
