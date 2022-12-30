package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeFormPage {
    //TODO дописать локаторы
    @FindBy(id = "firstName")
    private WebElement firstName;
    @FindBy(id = "lastName")
    private WebElement lastName;
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy()
    private WebElement gender;
    @FindBy(id = "userNumber")
    private WebElement userNumber;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;
    @FindBy(id = "submit")
    private WebElement submit;

    PracticeFormPage(final WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void enterForm(final String fName, final String lName){
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        submit.submit();
    }



}
