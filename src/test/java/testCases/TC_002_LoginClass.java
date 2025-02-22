package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginClass extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void verifyLogin() {
        log.info("**** Starting TC_002_LoginTest ****");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(properties.getProperty("email"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLogin();
            log.info("Clicked Login button");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            Assert.assertTrue(myAccountPage.isMyAccountHeadingPresent(), "'My Account' heading is not present");
            log.info("'My Account' verified");
        } catch (Exception e) {
            Assert.fail();
        } finally {
            log.info("**** Finished TC_002_LoginTest ****");
        }
    }

}
