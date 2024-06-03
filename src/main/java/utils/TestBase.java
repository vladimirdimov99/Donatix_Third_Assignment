package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties properties;
    String browserName;

    public TestBase() {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/main/java/utils/config.properties");
            properties.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeBrowserDriver() throws Exception {
        browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new Exception("Unsupported browser!!!");
        }
    }

    public void loadTheWebsite() throws IOException {
        // Another way to do that and immediately login to the website -->
        // https://admin:admin@the-internet.herokuapp.com/basic_auth
        // driver.get(properties.getProperty("protocol") + properties.getProperty("username") + ":"
        // + properties.getProperty("password")+ "@" + properties.getProperty("url"));

        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
    }

    public void quitTheDriver() {
        if (browserName == "chrome") {
            driver.quit();
        } else {
            driver.close();
        }
    }
}
