package restfullbooker;

import entity.restfullbooker.getbooking.Booking;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import utils.FileUtils;

import java.io.IOException;

public class CreateBookingStepDefinition extends AbstractBaseStepDefinition{

    private Booking bookingRq;

    @Given("set base uri {string} for create feature")
    public void setBaseUrl(String url){
        httpManager.setBaseUrl(url);
    }

    @Given("make a body from {string} file")
    public void makeABodyFromFile(String filePath){
        try {
            bookingRq = FileUtils.readJsonFile(FileUtils.TEST_REQUESTS_PATH + filePath, Booking.class);
        }catch (IOException e){
            throw new AssertionError("There is a problem due reading file with path "+ filePath, e);
        }
    }

    @When("do POST with body")
    public void createBooking(){
       response = httpManager.sendPost("", bookingRq.toJsonString());
    }

    @Then("response contains info from request body")
    public void checkResponseInfo(){
        response.then().statusCode(200);
        //TODO не парсится в bookingDates через response.then().extract().path("booking.");
        Booking actualBooking = response.then().extract().jsonPath().getObject("booking", Booking.class);
        Assertions.assertEquals(bookingRq, actualBooking);
    }


}
