package restfullbooker;

import entity.restfullbooker.getbooking.BookingId;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

public class GetBookingIDsStepDefinition extends AbstractBaseStepDefinition{
    private Map<String, String> queryMap;

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
            MatcherAssert.assertThat(idsArray, Matchers.notNullValue());
        }catch (NullPointerException e){
            throw new AssertionError("Cannot find any objects in the response", e);
        }
    }

    @Given("name and surname for filtering")
    public void setNameQueryParams(Map<String, String> map){
        queryMap = map;
    }

    @When("do GET with query firstname and lastname with path {string}")
    public void sendGetWithNameFilter(String path){
        response = httpManager.sendGet(path, queryMap);
    }

    @Then("response contains this person booking")
    public void checkPersonsBooking(){
        Assertions.assertEquals(200, response.statusCode());
        try {
            List<BookingId> idsArray = response.then().extract().jsonPath().getList("");
            MatcherAssert.assertThat(idsArray, Matchers.notNullValue());
        }catch (NullPointerException e){
            throw new AssertionError("Cannot find any objects in the response", e);
        }
    }

    @Given("dates for filtering")
    public void setDateQueryParams(Map<String, String> map){
        queryMap = map;
    }

    @When("do GET with date filters and path {string}")
    public void sendGetWithDatesFilter(String path){
        response = httpManager.sendGet(path, queryMap);
    }

    @Then("response contains these dates booking")
    public void checkDatesBooking(){
        Assertions.assertEquals(200, response.statusCode());
        try {
            List<BookingId> idsArray = response.then().extract().jsonPath().getList("");
            MatcherAssert.assertThat(idsArray, Matchers.notNullValue());
        }catch (NullPointerException e){
            throw new AssertionError("Cannot find any objects in the response", e);
        }
    }

}
