package theinternet;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scenarios.WebDriverSteps;
import utils.FileUtils;

import java.util.Properties;

public class BasicAuthTest extends WebDriverSteps {
    private WebDriver driver;
    private static String url;
    private String excpectedResult = "Congratulations! You must have the proper credentials.";


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
        String login = "admin", password = "admin";
        String secrets = login + ":" + password;
        String uri = url.substring(7);
        driver.get("http://"+secrets+"@"+uri);

       String actualResult = driver.findElement(By.xpath("//*/div/div[@class = 'example']/p")).getText();
       Assertions.assertEquals(excpectedResult, actualResult);
    }
}
