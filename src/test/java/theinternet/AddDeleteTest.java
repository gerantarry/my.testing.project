package theinternet;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import scenarios.WebDriverSteps;
import utils.FileUtils;

import java.util.List;
import java.util.Properties;

public class AddDeleteTest extends WebDriverSteps {
    //url сайта
    private static String url;
    //driver
    private WebDriver driver;
    //xpath элементов
    final String addButtonXpath = "//*[@class = 'example']/button";
    final String deleteButtonXpath = "//*[@id = 'elements']/button";

    @BeforeAll
    public static void preSetUp(){
        Properties urls = FileUtils.readPropertiesFile("src/test/resources/url.properties");
        if (urls != null){
            url = urls.getProperty("add_remove_url");
        }else {
            throw new NullPointerException("urls == null");
        }
    }

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    //При нажатии появляется кнопка, при нажатии появившейся кнопки она удаляется
    public void addRemoveOneClickTest(){
      driver.get(url);
      WebElement addButton = driver.findElement(By.xpath(addButtonXpath));
      addButton.click();

      WebElement deleteButton = driver.findElement(By.xpath(deleteButtonXpath));
      deleteButton.click();

      Assertions.assertThrows(NoSuchElementException.class,
              () -> driver.findElement(By.xpath(deleteButtonXpath)));
    }

    @Test
    //При N нажатии появляются N элементов кнопок
    public void addRemoveFiveClickTest(){
        driver.get(url);
        WebElement addButton = driver.findElement(By.xpath(addButtonXpath));
        for (int i = 0; i<5; i++){
            addButton.click();
        }

        WebElement elements = driver.findElement(By.xpath("//*[@id = 'elements']"));
        List<WebElement> deleteButtonsList = elements.findElements(By.className("added-manually"));

        Assertions.assertEquals(5, deleteButtonsList.size(), "Количество кнопок не совпадает с количеством нажатий");
    }

}
