package simple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import scenarios.WebDriverSteps;

public class GoogleTest extends WebDriverSteps {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @AfterEach
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void searchTest(){
        WebElement inputElement = driver.findElement(By.xpath("//input[@name='q']"));
        inputElement.sendKeys("Сбербанк");
        inputElement.submit();
    }
}
