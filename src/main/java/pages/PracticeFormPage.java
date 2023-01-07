package pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

//TODO Use yandex wrapper lib for WebElements
public class PracticeFormPage {
    @FindBy(id = "firstName")
    private TextInput firstName;
    @FindBy(id = "lastName")
    private TextInput lastName;
    @FindBy(id = "userEmail")
    private TextInput userEmail;
    //Gender
    @FindBy(xpath = "//input[@type = 'radio' and @value = 'Male']")
    private WebElement male;
    @FindBy(xpath = "//input[@type = 'radio' and @value = 'Female']")
    private WebElement female;
    @FindBy(xpath = "//input[@type = 'radio' and @value = 'Other']")
    private WebElement otherGender;

    @FindBy(id = "userNumber")
    private WebElement userNumber;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;
    //Hobbies
    @FindBy(id = "hobbies-checkbox-1")
    private WebElement sports;
    @FindBy(id = "hobbies-checkbox-2")
    private WebElement reading;
    @FindBy(id = "hobbies-checkbox-3")
    private WebElement music;

    @FindBy(id = "subjectsInput")
    private WebElement subject;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(id = "submit")
    private WebElement submit;

    private final WebDriver driver;

    public final static String READING = "Reading", SPORTS = "Sports", MUSIC = "Music";
    public final static String MALE = "Male", FEMALE = "Female", OTHER_GENDER = "Other";

    public PracticeFormPage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    /**
     *
     * @param number - only digits allowed
     * @param gender for the radiobutton. Allowed values: Male, Female, Other. Use public constants of the class.
     */
    public void enterForm(final String fName, final String lName, final String number, final String gender){
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        userNumber.sendKeys(number);
        setGender(gender);
        submit.submit();
    }

    /**
     * Set subject to the Subject input
     */
    public void setSubjects(String ... subjects){
        for (String sbj : subjects){
            subject.sendKeys(sbj);
        }
    }

    /**
     * Choose and set gender radiobutton
     * Allowed values: Male, Female, Other. Use public constants of the class.
     * @param gender for the radiobutton
     */
    public void setGender(String gender){
        switch (gender){
            case MALE: jsRadioButtonClick(male);
            break;
            case FEMALE: jsRadioButtonClick(female);
            break;
            default:
                jsRadioButtonClick(otherGender);
        }
    }

    /**
     * This method needs for correct click.
     * There is an article where you can find explanation of this case.
     * <a href="https://goo.su/D64Ij"> click here</a>
     * @param radio button for clicking
     */
    private void jsRadioButtonClick(WebElement radio) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", radio);
    }

    /**
     * Choose and set hobbies checkboxes
     * Allowed values: Sport, Reading, Music. Use public constants of the class.
     * @param hobbies for the checkboxes
     */
    public void setHobbies(String ... hobbies){
        for (String hobbie : hobbies){
            switch (hobbie){
                case READING: clickCheckBoxByAction(reading);
                break;
                case SPORTS: clickCheckBoxByAction(sports);
                break;
                case MUSIC: clickCheckBoxByAction(music);
                break;
                default:
                    throw  new IllegalArgumentException("parameter 'hobbie' has incorrect value: " + hobbie);
            }
        }
    }

    private void clickCheckBoxByAction(WebElement webElement){
        Actions action = new Actions(driver);
        action.moveToElement(webElement).click().build().perform();
    }

    /**
     * Check hobbies checkboxes
     * Allowed values: Sport, Reading, Music. Use public constants of the class.
     * @param hobbie for the checkboxes
     */
    public boolean checkHobbieSelection(String hobbie){
        boolean isSelected;
        switch (hobbie){
            case READING: isSelected = reading.isSelected();
                break;
            case SPORTS: isSelected = sports.isSelected();
                break;
            case MUSIC: isSelected = music.isSelected();
            break;
            default:
                throw  new IllegalArgumentException("parameter 'hobbie' has incorrect value: " + hobbie);
        }
        return isSelected;
    }

    /**
     * Check selection of the gender radion buttons group
     * @return indicator
     */
    public boolean checkGenderSelection(){
        return male.isSelected()
                || female.isSelected()
                || otherGender.isSelected();
    }

    public WebElement getSelectedGender(String gender){
        switch (gender){
            case MALE:
                return male;
            case FEMALE:
                return female;
            default:
                return otherGender;
        }
    }
}
