package theinternet;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scenarios.WebDriverSteps;
import utils.FileUtils;

import java.time.Duration;
import java.util.Properties;

public class BasicAuthTest extends WebDriverSteps {
    private WebDriver driver;
    private static String url;

    @BeforeAll
    public static void preSetUp(){
        Properties prop = FileUtils.readPropertiesFile("src/test/resources/url.properties");
        if (prop!=null){
            url = prop.getProperty("basic_auth_url");
        }else{
            throw new NullPointerException("url == null");
        }
    }

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }

    @Test
    public void successAuthTest() {
        driver.get(url);
        //TODO понять как рабоать с эти всплывающим окном. Алерт ли это или нет?
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent());
    }
}
