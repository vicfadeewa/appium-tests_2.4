import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

class SecondActivityPage {
    private final AndroidDriver driver;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    private MobileElement resultText;

    public SecondActivityPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getResultText() {
        return resultText.getText();
    }
}
