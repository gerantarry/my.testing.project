package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:cucumber/features/"},
        glue = {"demoqa/", "restfullbooker/"})
public class RunCucumberTest {
}
