package testCases;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger log;
    public Properties properties;

    @BeforeClass(groups = {"Sanity", "Regression", "Master", "DDT"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {
        FileReader propertiesFile = new FileReader("./src//test//resources//config.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        log = LogManager.getLogger(this.getClass());

        if (properties.getProperty("execution_environment").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Incorrect browser name specified in XML");
                    return;
            }
        } else if (properties.getProperty("execution_environment").equalsIgnoreCase("remote")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            if (os.equalsIgnoreCase("windows")) {
                desiredCapabilities.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("mac")) {
                desiredCapabilities.setPlatform(Platform.MAC);
            } else if (os.equalsIgnoreCase("linux")) {
                desiredCapabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("Incorrect OS specified in XML");
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    desiredCapabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    desiredCapabilities.setBrowserName("MicrosoftEdge");
                    break;
                case "firefox":
                    desiredCapabilities.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("Incorrect browser name specified in XML");
                    return;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        } else {
            System.out.println("Incorrect execution environment specified in properties file");
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appURL")); //reading URL from Properties file
        driver.manage().window().maximize();
    }

    public String randomString(int nrOfCharacters) {
        return RandomStringUtils.randomAlphabetic(nrOfCharacters);
    }

    public String randomNumber(int nrOfNumbers) {
        return RandomStringUtils.randomNumeric(nrOfNumbers);
    }

    public String randomAlphaNumeric(int nrOfCharacters, int nrOfNumbers) {
        return RandomStringUtils.randomAlphabetic(nrOfCharacters) + "$G" + RandomStringUtils.randomNumeric(nrOfNumbers);
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master", "DDT"})
    public void tearDown() {
        driver.quit();
    }

    public String captureScreenshot(String testName) {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy_HH;mm;ss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }

}
