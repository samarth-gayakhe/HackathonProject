package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"./src/test/resources/features"},
        //features= {".//chair.feature"},
//        features= {"@target/rerun.txt"},
        glue = {"stepDefinitions", "utilities", "hooks"},
        plugin = {
                "pretty", "html:cucumber-reports/cucumber-html-report.html",
//                "pretty", "html:cucumber-reports/cucumber-failed-rerun-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true
)
public class test {
}