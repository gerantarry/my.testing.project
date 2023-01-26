package restfullbooker;


import entity.restfullbooker.getbooking.Booking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class GetBookingStepDefinition extends AbstractBaseStepDefinition {

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
}
