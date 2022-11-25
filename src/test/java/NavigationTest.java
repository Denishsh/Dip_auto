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

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NavigationTest {

    private AndroidDriver driver;

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
    public void moveToAboutTabTest() {
        MobileElement menuBtn = (MobileElement) driver.findElementByAccessibilityId("Главное меню");
        menuBtn.click();
        MobileElement aboutTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        aboutTab.click();
        MobileElement backButton = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/about_back_image_button");
        backButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // toast waiting
        MobileElement news = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.TextView");
        news.isDisplayed();
    }

    @Test
    public void moveToTasksTabTest() {
        MobileElement menuBtn = (MobileElement) driver.findElementByAccessibilityId("Главное меню");
        menuBtn.click();
        MobileElement tasksTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        tasksTab.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // toast waiting
        MobileElement tasks = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView");
        tasks.isDisplayed();
    }

    @Test
    public void moveToNewsTabTest() {
        MobileElement menuBtn = (MobileElement) driver.findElementByAccessibilityId("Главное меню");
        menuBtn.click();
        MobileElement newsTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        newsTab.click();
        MobileElement errToast = (MobileElement) driver.findElementById("android:id/button1");
        if (errToast.isDisplayed()) {
            errToast.click();
        }
        MobileElement news = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView");
        news.isDisplayed();
    }

    @Test
    public void moveToMainTabTest() {
        MobileElement menuBtn = (MobileElement) driver.findElementByAccessibilityId("Главное меню");
        menuBtn.click();
        MobileElement mainTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        mainTab.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // toast waiting
        MobileElement news = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.TextView");
        news.isDisplayed();
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
