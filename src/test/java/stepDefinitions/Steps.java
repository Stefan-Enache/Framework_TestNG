package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Steps {

    WebDriver driver;
    @Given("the user is on the login page")
    public void the_user_is_on_the_nop_commerce_login_page() {
        driver = new ChromeDriver();
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @When("the user enters valid credentials {string}, {string}")
    public void the_user_enters_valid_credentials_username_password(String username, String password) {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
    }

    @When("the user clicks on the login button")
    public void the_user_clicks_on_the_login_page() {
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Then("the user should be redirected to the My Account page")
    public void the_user_should_be_redirected_to_the_my_account_page() {
        boolean status = driver.findElement(By.xpath("//h2[normalize-space()='My Account']")).isDisplayed();
        Assert.assertTrue(status);
    }

}
