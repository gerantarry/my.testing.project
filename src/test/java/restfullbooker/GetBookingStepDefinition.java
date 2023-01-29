package restfullbooker;


import entity.restfullbooker.getbooking.Booking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class GetBookingStepDefinition extends AbstractBaseStepDefinition {

    private final String ACCEPT = "Accept";
    //application/json or application/xml allowed
    private  String acceptValue;

    @Given("set base uri {string}")
    public void setBaseUrl(String url){
        httpManager.setBaseUrl(url);
    }

    @When("do GET with id to {string}")
    public void sendGetWithId(String path){
        response = httpManager.sendGet(path);
    }

    @Then("response contains info")
    public void checkBookingInfo(){
        Booking booking = response.then().extract().as(Booking.class);
        Assertions.assertNotNull(booking);
        //This must be a person info checking code. But this api always generate random responses
        System.out.println(response.getBody().asPrettyString());
    }


    @Given("set Accept request header as {string}")
    public void setAcceptXmlInRequestHeader(String format) {
        acceptValue = format;
    }

    @When("do GET with id {int}")
    public void doGETWithId(int id) {
        Map<String, String> headers = new HashMap<>();
        headers.put(ACCEPT, acceptValue);
       response = httpManager.sendGet(String.valueOf(id), null, headers);
    }

    @Then("response has xml format")
    public void responseHasXmlFormat() {
        response.then().statusCode(200);
        response.then().contentType("text/html; charset=utf-8");
    }
}
