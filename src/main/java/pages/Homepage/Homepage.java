package pages.Homepage;

import org.openqa.selenium.By;
import utils.TestBase;

import java.io.IOException;

public class Homepage extends TestBase {

    public By notAuthorizedMessageLocator = By.tagName("body");

    public By loginSuccessMessageLocator = By.tagName("p");

    public void loginWithValidCredentials() throws IOException {
        Runtime.getRuntime().exec("src/main/resources/autoIt_tool_files/LoginWithValidCredentials.exe");
    }

    public void loginWithInvalidCredentials() throws IOException {
        Runtime.getRuntime().exec("src/main/resources/autoIt_tool_files/LoginWithInvalidCredentials.exe");
    }
}
