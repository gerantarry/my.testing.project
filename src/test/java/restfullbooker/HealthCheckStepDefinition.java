package restfullbooker;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class HealthCheckStepDefinition extends AbstractBaseStepDefinition {

    @When("do GET request to {string}")
    public void do_get_request_to(String string) {
        response = httpManager.sendGet(string);
    }
    @Then("response status code is {int}")
    public void response_status_code_is(Integer int1) {
       Assertions.assertEquals(int1, response.statusCode(), "there is difference in status code. Check response!");
    }

}
