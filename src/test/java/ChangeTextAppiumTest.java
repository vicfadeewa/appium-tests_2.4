import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class ChangeTextAppiumTest {
    private AndroidDriver driver;
    private MainActivityPage mainPage;
    private SecondActivityPage secondPage;
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
        desiredCapabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 120000);
        desiredCapabilities.setCapability("appium:adbExecTimeout", 120000);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        mainPage = new MainActivityPage(driver);
        secondPage = new SecondActivityPage(driver);
    }

    @Test
    public void testChangeText() {
        mainPage.setUserInput(TEXT_TO_SET);
        mainPage.clickChangeButton();
        Assertions.assertEquals(TEXT_TO_SET, mainPage.getTextToBeChanged());
    }

    @Test
    public void testEmptyStringInput() {
        String originalText = mainPage.getTextToBeChanged();
        mainPage.setUserInput("   ");
        mainPage.clickChangeButton();
        String updatedText = mainPage.getTextToBeChanged();
        Assertions.assertEquals(originalText, updatedText);
    }

    @Test
    public void testTextInNewActivity() {
        String textToSet = "Non-empty text";
        mainPage.setUserInput(textToSet);
        mainPage.clickActivityButton();
        Assertions.assertEquals(textToSet, secondPage.getResultText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}