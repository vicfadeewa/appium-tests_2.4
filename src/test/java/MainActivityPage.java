import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

class MainActivityPage {
    private final AndroidDriver driver;

    // Элементы страницы
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    private MobileElement userInput;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    private MobileElement buttonChange;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    private MobileElement buttonActivity;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    private MobileElement textToBeChanged;

    public MainActivityPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void setUserInput(String text) {
        userInput.clear();
        userInput.sendKeys(text);
    }

    public void clickChangeButton() {
        buttonChange.click();
    }

    public void clickActivityButton() {
        buttonActivity.click();
    }

    public String getTextToBeChanged() {
        return textToBeChanged.getText();
    }
}
