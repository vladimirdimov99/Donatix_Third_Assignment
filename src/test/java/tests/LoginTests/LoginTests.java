package tests.LoginTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Homepage.Homepage;
import utils.TestBase;

import java.io.IOException;
import java.time.Duration;

public class LoginTests extends TestBase {

    Homepage homepage = new Homepage();
    Duration timeout = Duration.ofSeconds(3);

    @BeforeMethod
    void setUp() throws Exception {
        initializeBrowserDriver();
        loadTheWebsite();
    }

    @Test
    void loginWithValidCredentials() throws IOException {
        homepage.loginWithValidCredentials();

        new WebDriverWait(driver, timeout.getSeconds()).until(ExpectedConditions.presenceOfElementLocated(homepage.loginSuccessMessageLocator));
        String loginSuccessMessage = driver.findElement(homepage.loginSuccessMessageLocator).getText();
        Assert.assertEquals(loginSuccessMessage, "Congratulations! You must have the proper credentials.", "The Login success message is missing!!!");
    }

    @Test
    void loginWithInvalidCredentials() throws IOException {
        homepage.loginWithInvalidCredentials();

        new WebDriverWait(driver, timeout.getSeconds()).until(ExpectedConditions.presenceOfElementLocated(homepage.notAuthorizedMessageLocator));
        boolean isNotAuthorizedDisplayed = driver.findElement(homepage.notAuthorizedMessageLocator).isDisplayed();
        Assert.assertTrue(isNotAuthorizedDisplayed, "The Not authorized message is not displayed!!!");
    }

    @AfterMethod
    void shutDownTheDriver() {
        quitTheDriver();
    }
}
