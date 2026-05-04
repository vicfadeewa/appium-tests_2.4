package ru.netology.qa;

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


public class SampleTest {

    private AndroidDriver driver;

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
        desiredCapabilities.setCapability("appium:appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        // Добавляем таймауты С ПРЕФИКСОМ appium:
        desiredCapabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 90000); // 90 секунд
        desiredCapabilities.setCapability("appium:adbExecTimeout", 90000); // 90 секунд

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el7 = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
        el7.isDisplayed();
        el7.click();

        MobileElement el8 = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/op_add"));
        el8.isDisplayed();
        el8.click();

        MobileElement el9 = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
        el9.isDisplayed();
        el9.click();

        MobileElement el10 = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/eq"));
        el10.isDisplayed();
        el10.click();

        MobileElement el11 = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/result_final"));
        el11.isDisplayed();
        Assertions.assertEquals("4", el11.getText());
        }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
