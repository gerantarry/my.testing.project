package toolsQa;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
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

    @Given("prepare urls {string}")
    public void prepareUrls(String alias) {
        final Properties urls = FileUtils.readPropertiesFile(FileUtils.URLS_FILE_PATH);
        if (urls != null){
            url = urls.getProperty(alias);
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

    @Given("Hobbies didn't select")
    public void hobbiesDidntSelect() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(formPage.checkHobbieSelection(PracticeFormPage.SPORTS)),
                () -> Assertions.assertFalse(formPage.checkHobbieSelection(PracticeFormPage.READING)),
                () -> Assertions.assertFalse(formPage.checkHobbieSelection(PracticeFormPage.MUSIC))
        );
    }

    @When("Student picks a <hobbie>")
    public void studentPicksAHobbie(String hobbie) {
        formPage.setHobbies(hobbie);
       Assertions.assertTrue(formPage.checkHobbieSelection(hobbie));
    }
}
