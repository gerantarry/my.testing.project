package demoqa;

import entity.Student;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PracticeFormPage;
import scenarios.WebDriverSteps;
import utils.FileUtils;

import java.util.Properties;

public class PracticeFormTest extends WebDriverSteps {

    private WebDriver driver;
    private PracticeFormPage formPage;

    @Given("Prepare urls {string}")
    public void prepareUrls(String alias) {
        final Properties urls = FileUtils.readPropertiesFile(FileUtils.URLS_FILE_PATH);
        String url;
        if (urls != null){
            url = urls.getProperty(alias);
        }else {
            throw new NullPointerException("urls == null");
        }
        driver = new ChromeDriver();
        driver.get(url);
        formPage = new PracticeFormPage(driver);
    }

    @After(value = "@UI")
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Given("Student is on the reg. form page")
    public void studentIsOnTheRegFormPage() {
        Assertions.assertNotNull(formPage);
    }

    @When("Student inputs his data into the form")
    public void studentInputsHisDataIntoTheForm() {
        Student steve = ArgumentStepDefinition.STUDENTS.get(0);
        formPage.enterForm(steve.getFirstName(), steve.getSecondName(), steve.getPhoneNumber(), steve.getGender());
    }

    @Then("Submit goes correct")
    public void submitGoesCorrect() {
        Student steve = ArgumentStepDefinition.STUDENTS.get(0);
        driver.findElement(By.xpath("//tbody/tr/td[text() = '"+steve.getFirstName()+" "+steve.getSecondName()+"']"));
        driver.findElement(By.xpath("//tbody/tr/td[text() = '"+steve.getPhoneNumber()+"']"));
        driver.findElement(By.xpath("//tbody/tr/td[text() = '"+steve.getGender()+"']"));
    }

    @Given("Hobbies didn't select")
    public void hobbiesDidntSelect() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(formPage.checkHobbieSelection(PracticeFormPage.SPORTS)),
                () -> Assertions.assertFalse(formPage.checkHobbieSelection(PracticeFormPage.READING)),
                () -> Assertions.assertFalse(formPage.checkHobbieSelection(PracticeFormPage.MUSIC))
        );
    }

    @When("Student picks a {word}")
    public void studentPicksAHobbie(String word) {
        formPage.setHobbies(word);
    }

    @Then("The {word} checkbox becomes selected")
    public void theHobbieCheckboxBecomesSelected(String word) {
        Assertions.assertTrue(formPage.checkHobbieSelection(word));
    }

    @Given("Gender didn't selected")
    public void genderDidntSelected() {
        Assertions.assertFalse(formPage.checkGenderSelection());
    }

    @When("Student picks a gender {word}")
    public void studentPickAGender(String word) {
        formPage.setGender(word);
    }

    @Then("The {word} radio becomes selected")
    public void theGenderRadioBecomesSelected(String word) {
       WebElement selected = formPage.getSelectedGender(word);
      Assertions.assertTrue(selected.isSelected());
    }
}
