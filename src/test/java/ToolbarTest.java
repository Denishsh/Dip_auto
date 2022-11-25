import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ToolbarTest {

    private AndroidDriver driver;
    public static final String LOGIN = "login2";
    public static final String PASSWORD = "password2";

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

        // auth
        new StartScreenTest().auth(driver);
    }

    @Test
    public void toolbarTitleTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement toolbarTitle = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/trademark_image_view");
        toolbarTitle.isDisplayed();
    }

    @Test
    public void toolbarMissionTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement ourMission = (MobileElement) driver.findElementByAccessibilityId("Наша Миссия");
        ourMission.click();
        MobileElement mainMission = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/our_mission_title_text_view");
        mainMission.isDisplayed();
        driver.navigate().back();
    }

    @Test
    public void toolbarMenuTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement menuBtn = (MobileElement) driver.findElementByAccessibilityId("Главное меню");
        menuBtn.click();
        MobileElement mainTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        mainTab.isDisplayed();
        MobileElement tasksTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        mainTab.isDisplayed();
        MobileElement newsTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        newsTab.isDisplayed();
        MobileElement aboutTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        aboutTab.isDisplayed();
        driver.navigate().back();
    }

    @Test
    public void toolbarExitTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement exitBtn = (MobileElement) driver.findElementByAccessibilityId("Авторизация");
        exitBtn.click();
        MobileElement exitConfirmBtn = (MobileElement) driver.findElementById("android:id/title");
        exitConfirmBtn.click();
        MobileElement auth = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView");
        auth.isDisplayed();
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
