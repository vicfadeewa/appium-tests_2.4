import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ChangeTextAppiumTest {

    private AndroidDriver driver;
    private static final String TEXT_TO_SET = "Netology";

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel_4_API_33");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 90000);
        desiredCapabilities.setCapability("appium:adbExecTimeout", 90000);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void testChangeText() {
        MobileElement userInput = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        userInput.clear();
        userInput.sendKeys(TEXT_TO_SET);

        MobileElement buttonChange = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        buttonChange.click();

        MobileElement textToBeChanged = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        Assertions.assertEquals(TEXT_TO_SET, textToBeChanged.getText());
    }

    @Test
    public void testEmptyStringInput() {
        MobileElement textToBeChanged = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        String originalText = textToBeChanged.getText();

        MobileElement userInput = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        userInput.clear();
        userInput.sendKeys("   ");

        MobileElement buttonChange = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        buttonChange.click();

        String updatedText = textToBeChanged.getText();
        Assertions.assertEquals(originalText, updatedText);
    }

    @Test
    public void testTextInNewActivity() {
        String textToSet = "Non-empty text";

        MobileElement userInput = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        userInput.clear();
        userInput.sendKeys(textToSet);

        MobileElement buttonActivity = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        buttonActivity.click();

        driver.manage().timeouts().implicitlyWait(15, java.util.concurrent.TimeUnit.SECONDS);

        MobileElement resultText = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        Assertions.assertEquals(textToSet, resultText.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
