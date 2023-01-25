package restfullbooker;

import entity.restfullbooker.getbooking.BookingId;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class GetBookingIDsStepDefinition  extends AbstractBaseStepDefinition{

    @Given("set base url {string}")
    public void setBaseUrl(String url){
        httpManager.setBaseUrl(url);
    }

    @When("do GET with path {string}")
    public void sendGetForIds(String path){
        response = httpManager.sendGet(path);
    }

    @Then("response contains array of objects with booking ids")
    public void checkIdsArray(){
        Assertions.assertEquals(200, response.statusCode());
        try {
            List<BookingId> idsArray = response.then().extract().jsonPath().getList("");
        }catch (NullPointerException e){
            throw new AssertionError("Cannot find any objects in the response", e);
        }
    }


}
