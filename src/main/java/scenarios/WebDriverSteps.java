package scenarios;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSteps {

    //init
    {
        setupChromeDriver();
        setupFirefoxDriver();
    }


    public void setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
    }

    public void setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
    }
}
