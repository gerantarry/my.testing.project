package toolsQa;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import scenarios.WebDriverSteps;
import utils.FileUtils;

import java.util.Properties;

public class PracticeFormTest extends WebDriverSteps {

    private static String url;
    private WebDriver driver;

    @Given("prepare urls")
    public void prepareUrls() {
        final Properties urls = FileUtils.readPropertiesFile("src/test/resources/url.properties");
        if (urls != null){
            url = urls.getProperty("reg_form_url");
        }else {
            throw new NullPointerException("urls == null");
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Given("Student is on the reg. form page")
    public void studentIsOnTheRegFormPage() {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("Student inputs his data into the form")
    public void studentInputsHisDataIntoTheForm() {
        //TODO Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Submit goes correct")
    public void submitGoesCorrect() {
        //TODO Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
