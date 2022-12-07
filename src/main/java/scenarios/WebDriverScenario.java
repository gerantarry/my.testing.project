package scenarios;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverScenario {
//Стоит ли сделать WebdriverSteps с шагами установки драйвера?
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
