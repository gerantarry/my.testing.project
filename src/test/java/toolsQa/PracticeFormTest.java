package toolsQa;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
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

    @BeforeAll
    public static void before_all(){
       final Properties urls = FileUtils.readPropertiesFile("src/test/resources/url.properties");
        if (urls != null){
            url = urls.getProperty("reg_form_url");
        }else {
            throw new NullPointerException("urls == null");
        }
    }

    @Before
    public void set_up(){
        driver = new ChromeDriver();
    }

    @After
    public void tear_down(){
        driver.quit();
    }

    @Given("Student is on the reg. form page")
    public void student_is_on_the_reg_form_page() {
        driver.get(url);
    }

    @When("Student inputs his data into the form")
    public void student_inputs_his_data_into_the_form() {
        //TODO Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("Submit goes correct")
    public void submit_goes_correct() {
        //TODO Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
