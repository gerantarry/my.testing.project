package restfullbooker;

import entity.restfullbooker.AuthRqDto;
import http.manager.v1.HttpManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.Matchers.is;

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

    @When("do POST to {string}")
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
        System.out.println("The token = "+token);
    }

    @Given("request body {word} and {word}")
    public void setInvalidRqBody(String username, String password) {
        AuthRqDto authRqDto = new AuthRqDto();
        authRqDto.setUsername(username);
        authRqDto.setPassword(password);
        requestBody = authRqDto.toJsonString();
    }

    @When("do POST with invalid data to {string}")
    public void sendPostRequestWithInvalidData(String uri){
       response = httpManager.sendPost(uri, requestBody);
    }

    @Then("response contains reason {string}")
    public void checkStatusCodeNot200(String expReason){
       String actualReason = response.then().extract().jsonPath().get("reason");
       MatcherAssert.assertThat(response.statusCode(), is(200));
       MatcherAssert.assertThat(actualReason, is(expReason));
    }
}
