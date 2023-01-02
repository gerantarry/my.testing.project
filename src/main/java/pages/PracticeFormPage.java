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
    //Gender
    @FindBy(xpath = "label[text() = 'Male']")
    private WebElement male;
    @FindBy(xpath = "label[text() = 'Female']")
    private WebElement female;
    @FindBy(id = "gender-radio-3")
    private WebElement otherGender;

    @FindBy(id = "userNumber")
    private WebElement userNumber;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;
    //Hobbies
    @FindBy(id = "hobbies-checkbox-1")
    private WebElement sport;
    @FindBy(xpath = "//label[text() = 'Reading']")
    private WebElement reading;
    @FindBy(xpath = "label[text() = 'Music']")
    private WebElement music;

    @FindBy(id = "subjectsInput")
    private WebElement subject;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    @FindBy(id = "submit")
    private WebElement submit;

    public final static String READING = "Reading", SPORT = "Sport", MUSIC = "Music";
    public final static String MALE = "Male", FEMALE = "Female", OTHER_GENDER = "Other";

    PracticeFormPage(final WebDriver driver){
        PageFactory.initElements(driver, this);
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
            case MALE: male.click();
            break;
            case FEMALE: female.click();
            break;
            case OTHER_GENDER: otherGender.click();
            default:
                throw new IllegalArgumentException("parameter 'gender' has incorrect value: " + gender);
        }
    }

    /**
     * Choose and set hobbies checkboxes
     * Allowed values: Sport, Reading, Music. Use public constants of the class.
     * @param hobbies for the checkboxes
     */
    public void setHobbies(String ... hobbies){
        for (String hobbie : hobbies){
            switch (hobbie){
                case READING: reading.click();
                break;
                case SPORT: sport.click();
                break;
                case MUSIC: music.click();
                default:
                    throw  new IllegalArgumentException("parameter 'hobbie' has incorrect value: " + hobbie);
            }
        }
    }



}
