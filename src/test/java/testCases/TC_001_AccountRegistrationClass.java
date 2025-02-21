package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_001_AccountRegistrationClass extends BaseClass {

    @Test(groups = {"Regression", "Master"})
    public void verifyRegistration() {
        log.info("**** Starting TC_001_AccountRegistrationTest ****");

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            log.info("Clicked on My Account");
            homePage.clickRegister();
            log.info("Clicked on Register");

            AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);
            accountRegistrationPage.setFirstName(randomString(6).toUpperCase());
            accountRegistrationPage.setLastName(randomString(5).toUpperCase());
            accountRegistrationPage.setEmail(randomString(8) + "@gmail.com");
            accountRegistrationPage.setTelephone(randomNumber(9));

            String password = randomAlphaNumeric(4, 3);
            accountRegistrationPage.setPassword(password);
            accountRegistrationPage.setConfirmPassword(password);
            accountRegistrationPage.setPrivacyPolicy();
            accountRegistrationPage.clickContinue();
            log.info("Filled input");

            if (accountRegistrationPage.getConfirmationMsg().equals("Your Account Has Been Created!")) {
                log.info("Verified 'Account Created!' message");
                Assert.assertTrue(true);
            } else {
                log.error("Registration failed: 'Your Account Has Been Created!' is not present");
                log.debug("Debug logs");
                Assert.fail();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            Assert.fail();
        } finally {
            log.info("**** Finished TC_001_AccountRegistrationTest ****");
        }

    }

}
