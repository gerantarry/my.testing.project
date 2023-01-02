package toolsQa;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PracticeFormPage;
import scenarios.WebDriverSteps;
import utils.FileUtils;

import java.util.Properties;

//TODO make more readable
public class PracticeFormTest extends WebDriverSteps {

    private static String url;
    private WebDriver driver;
    private PracticeFormPage formPage;

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
        formPage = new PracticeFormPage(driver);
    }

    @When("Student inputs his data into the form")
    public void studentInputsHisDataIntoTheForm() {
        formPage.enterForm("Steve", "Jenkins", "8847210572", PracticeFormPage.MALE);

    }

    @Then("Submit goes correct")
    public void submitGoesCorrect() {
        driver.findElement(By.xpath("//tbody/tr/td[text() = 'Steve Jenkins']"));
        driver.findElement(By.xpath("//tbody/tr/td[text() = '8847210572']"));
        driver.findElement(By.xpath("//tbody/tr/td[text() = 'Male']"));
    }

}
