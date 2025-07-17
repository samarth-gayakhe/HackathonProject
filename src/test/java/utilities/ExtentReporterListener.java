//package utilities;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import io.cucumber.java.After;
//import io.cucumber.java.AfterStep;
//import io.cucumber.java.Scenario;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static utilities.WebDriverManager.getDriver;
//
//public class ExtentReporterListener {
//
//    private static ExtentReports extent;
//    private static Map<Long, ExtentTest> threadToScenarioMap = new HashMap<>();
//
//    public ExtentReporterListener() {
//        if (extent == null) {
//            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            String reportName = "Test-Report-" + timestamp + ".html";
//            String path = System.getProperty("user.dir") + "/reports/" + reportName;
//            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//            reporter.config().setReportName("Hackathon Automation Report");
//            reporter.config().setDocumentTitle("Test Results");
//            extent = new ExtentReports();
//            extent.attachReporter(reporter);
//            extent.setSystemInfo("Tester", "Your Name");
//            extent.setSystemInfo("OS", System.getProperty("os.name"));
//            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
//
//            try {
//                Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/reports/"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @io.cucumber.java.Before
//    public void beforeScenario(Scenario scenario) {
//        long threadId = Thread.currentThread().getId();
//        ExtentTest test = extent.createTest(scenario.getName());
//        threadToScenarioMap.put(threadId, test);
//    }
//
//    @AfterStep
//    public void afterStep(Scenario scenario) throws IOException {
//        long threadId = Thread.currentThread().getId();
//        ExtentTest test = threadToScenarioMap.get(threadId);
//
//        if (scenario.isFailed()) {
//            test.log(Status.FAIL, "STEP FAILED: " + scenario.getName());
//            byte[] screenshotBytes = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
//            test.fail("Screenshot", com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(java.util.Base64.getEncoder().encodeToString(screenshotBytes)).build());
//        } else {
//            test.log(Status.PASS, "STEP PASSED: " + scenario.getName());
//        }
//    }
//
//    @After
//    public void afterScenario(Scenario scenario) {
//        long threadId = Thread.currentThread().getId();
//        ExtentTest test = threadToScenarioMap.get(threadId);
//
//        if (scenario.isFailed()) {
//            test.log(Status.FAIL, "SCENARIO FAILED: " + scenario.getName());
//        } else {
//            test.log(Status.PASS, "SCENARIO PASSED: " + scenario.getName());
//        }
//        threadToScenarioMap.remove(threadId);
//        extent.flush();
//    }
//}