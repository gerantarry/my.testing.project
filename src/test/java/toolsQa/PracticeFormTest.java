package toolsQa;

import dto.Student;
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

//TODO make more readable
public class PracticeFormTest extends WebDriverSteps {

    private WebDriver driver;
    private PracticeFormPage formPage;

    @Given("prepare urls {string}")
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

    @After
    public void tearDown(){
        driver.quit();
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

    @When("Student picks a Sports")
    public void studentPicksASports() {
        formPage.setHobbies(PracticeFormPage.SPORTS);
    }

    @Then("The Sports checkbox becomes selected")
    public void theSportsCheckboxBecomesSelected() {
        Assertions.assertTrue(formPage.checkHobbieSelection(PracticeFormPage.SPORTS));
    }

    @When("Student picks a Reading")
    public void studentPicksAReading() {
        formPage.setHobbies(PracticeFormPage.READING);
    }

    @Then("The Reading checkbox becomes selected")
    public void theReadingCheckboxBecomesSelected() {
        Assertions.assertTrue(formPage.checkHobbieSelection(PracticeFormPage.READING));
    }

    @When("Student picks a Music")
    public void studentPicksAMusic() {
        formPage.setHobbies(PracticeFormPage.MUSIC);
    }

    @Then("The Music checkbox becomes selected")
    public void theMusicCheckboxBecomesSelected() {
        Assertions.assertTrue(formPage.checkHobbieSelection(PracticeFormPage.MUSIC));
    }

    @Given("Gender didn't selected")
    public void genderDidntSelected() {
        Assertions.assertFalse(formPage.checkGenderSelection());
    }

    @When("Student picks a Male")
    public void studentPickAMale() {
        formPage.setGender(PracticeFormPage.MALE);
    }

    @Then("The Male radio becomes selected")
    public void theMaleRadioBecomesSelected() {
       WebElement selected = formPage.getSelectedGender(PracticeFormPage.MALE);
      Assertions.assertTrue(selected.isSelected());
    }

    @When("Student picks a Female")
    public void studentPickAFemale() {
        formPage.setGender(PracticeFormPage.FEMALE);
    }

    @Then("The Female radio becomes selected")
    public void theFemaleRadioBecomesSelected() {
        WebElement selected = formPage.getSelectedGender(PracticeFormPage.FEMALE);
        Assertions.assertTrue(selected.isSelected());
    }


    @When("Student picks a Dolphin")
    public void studentPickADolphin() {
        formPage.setGender(PracticeFormPage.OTHER_GENDER);
    }

    @Then("The Dolphin radio becomes selected")
    public void theDolphinRadioBecomesSelected() {
        WebElement selected = formPage.getSelectedGender(PracticeFormPage.OTHER_GENDER);
        Assertions.assertTrue(selected.isSelected());
    }
}
