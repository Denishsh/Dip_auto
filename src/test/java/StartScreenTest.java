import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@DisplayName("StartScreenTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StartScreenTest {
    private AndroidDriver driver;
    public static final String LOGIN = "login2";
    public static final String PASSWORD = "password2";
    public static final String WRONG_PASSWORD = "wrong_password";

    @BeforeAll
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(DEVICE_NAME, "RFCR60S83HK");
        desiredCapabilities.setCapability(APP_PACKAGE, "ru.iteco.fmhandroid");
        desiredCapabilities.setCapability(APP_ACTIVITY, "ru.iteco.fmhandroid.ui.AppActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appiumznewCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Description("Отображение сплэш-скрина при открытии приложения")
    @Test
    public void splashScreenTest() {
        MobileElement splashText = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/splashscreen_text_view");
        splashText.isDisplayed();
        allureSaveDeviceScreenshot(driver);
    }

    @Description("Neg. Авторизация под неправильными данными")
    @Test
    public void authWrongDataTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waiting splash screen ends
        MobileElement login = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText");
        login.sendKeys(LOGIN);
        MobileElement password = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText");
        password.sendKeys(WRONG_PASSWORD);
        MobileElement enter = (MobileElement) driver.findElementByAccessibilityId("Сохранить");
        enter.click();
        login.isDisplayed();
        allureSaveDeviceScreenshot(driver);
    }

    @Description("Авторизация под тестовыми данными (login2, password2)")
    @Test
    public void authTest() {
        auth(driver);
        MobileElement mainScreen = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/trademark_image_view");
        mainScreen.isDisplayed();
        allureSaveDeviceScreenshot(driver);
    }

    public void auth(AndroidDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waiting splash screen ends
        MobileElement login = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText");
        login.sendKeys(LOGIN);
        MobileElement password = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText");
        password.sendKeys(PASSWORD);
        MobileElement enter = (MobileElement) driver.findElementByAccessibilityId("Сохранить");
        enter.click();
    }

    public void allureSaveDeviceScreenshot(AndroidDriver driver) {
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
