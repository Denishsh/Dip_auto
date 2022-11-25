import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SplashScreen {
    @AndroidFindBy(id = "ru.iteco.fmhandroid:id/splashscreen_text_view")
    public MobileElement subscription;

    @WithTimeout(time = 2, chronoUnit = ChronoUnit.SECONDS)
    private AndroidDriver driver;

    public SplashScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
}
