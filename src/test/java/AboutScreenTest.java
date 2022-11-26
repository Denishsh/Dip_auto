import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AboutScreenTest {
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
        MobileElement menuBtn = (MobileElement) driver.findElementByAccessibilityId("Главное меню");
        menuBtn.click();
        MobileElement aboutTab = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
        aboutTab.click();
    }

    @Description("Отображение версии приложения")
    @Test
    public void versionTest() {
        MobileElement versionTitle = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/about_version_title_text_view");
        versionTitle.isDisplayed();
        MobileElement versionText = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/about_version_value_text_view");
        versionText.isDisplayed();
        new StartScreenTest().allureSaveDeviceScreenshot(driver);
    }

    @Description("Отображение копирайта, компании")
    @Test
    public void aboutCompanyTest() {
        MobileElement aboutCompanyText = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/about_company_info_label_text_view");
        aboutCompanyText.isDisplayed();
        new StartScreenTest().allureSaveDeviceScreenshot(driver);
    }

    @Description("Просмотр политики конфиденциальности")
    @Test
    public void privacyPoliticTest() {
        MobileElement privacyLink = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/about_privacy_policy_value_text_view");
        privacyLink.click();
        new StartScreenTest().allureSaveDeviceScreenshot(driver);
        driver.navigate().back();
    }

    @Description("Просмотр пользовательского соглашения")
    @Test
    public void userAgreementTest() {
        MobileElement userAgreementLink = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/about_terms_of_use_value_text_view");
        userAgreementLink.click();
        new StartScreenTest().allureSaveDeviceScreenshot(driver);
        driver.navigate().back();
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
