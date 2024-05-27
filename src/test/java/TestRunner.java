import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"pets", "store", "user"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:src/test/resources/reports/cucumber.json"},
        monochrome = true
)
public class TestRunner {
}
