package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"DDT", "Master"})
    public void verifyLoginDDT(String email, String password, String expected) {
        log.info("**** Starting TC_002_LoginTest ****");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin();
            log.info("Login performed");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean loginsStatus = myAccountPage.isMyAccountHeadingPresent();


            //  Data is valid  - login success - test pass - logout required
            //  Data is valid  - login failed  - test fail
            //
            // Data is invalid - login success - test fail - logout required
            // Data is invalid - login failed  - test pass


            if (expected.equalsIgnoreCase("Valid")) {
                if (loginsStatus) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail("Email and password are actually Invalid but the expected result is Valid");
                }
            }

            if (expected.equalsIgnoreCase("Invalid")) {
                if (loginsStatus) {
                    myAccountPage.clickLogout();
                    Assert.fail("Email and password are actually Valid but the expected result is Invalid");
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        } finally {
            log.info("**** Finished TC_002_LoginTest ****");
        }
    }

}
