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
public class TasksTest {
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
        MobileElement tasksTab = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/all_claims_text_view");
        tasksTab.click();
    }

    @Test
    public void emptyTest() {
        MobileElement emptyList = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/empty_claim_list_text_view");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        emptyList.isDisplayed();
    }

    @Test
    public void openFiltrationTest() {
        MobileElement filterBtn = (MobileElement) driver.findElementByAccessibilityId("Меню фильтрации списка заявок");
        filterBtn.click();
        MobileElement inProgressBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/item_filter_in_progress");
        inProgressBtn.click();
        MobileElement saveBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button");
        saveBtn.click();
    }

    // bug empty filter
    @Test
    public void emptyFiltrationTest() {
        MobileElement filterBtn = (MobileElement) driver.findElementByAccessibilityId("Меню фильтрации списка заявок");
        filterBtn.click();
        MobileElement openBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/item_filter_open");
        openBtn.click();
        MobileElement saveBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button");
        saveBtn.click();
    }

    @Test
    public void fullFiltrationTest() {
        MobileElement filterBtn = (MobileElement) driver.findElementByAccessibilityId("Меню фильтрации списка заявок");
        filterBtn.click();
        MobileElement openFilterBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/item_filter_open");
        openFilterBtn.click();
        MobileElement inProgressFilterBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/item_filter_in_progress");
        inProgressFilterBtn.click();
        MobileElement executedFilterBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/item_filter_executed");
        executedFilterBtn.click();
        MobileElement canceledFilterBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/item_filter_cancelled");
        canceledFilterBtn.click();
        MobileElement saveBtn = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button");
        saveBtn.click();
    }

    @Test
    public void moveToAddTaskTest() {
        MobileElement addBtn = (MobileElement) driver.findElementByAccessibilityId("Кнопка добавления новой заявки");
        addBtn.click();
        MobileElement creationTitle = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/custom_app_bar_title_text_view");
        creationTitle.isDisplayed();
        driver.navigate().back();
    }

    @Test
    public void emptyTaskCreationTest() {
        MobileElement addBtn = (MobileElement) driver.findElementByAccessibilityId("Кнопка добавления новой заявки");
        addBtn.click();
        MobileElement emptySave = (MobileElement) driver.findElementByAccessibilityId("Сохранить");
        emptySave.click();
        MobileElement emptyErr = (MobileElement) driver.findElementById("android:id/message");
        emptyErr.isDisplayed();
        MobileElement errBtn = (MobileElement) driver.findElementById("android:id/button1");
        errBtn.click();
        driver.navigate().back();
    }

    @Test
    public void addTaskTest() {
        MobileElement addBtn = (MobileElement) driver.findElementByAccessibilityId("Кнопка добавления новой заявки");
        addBtn.click();
        MobileElement title = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/title_edit_text");
        title.sendKeys("Test1");
        MobileElement executor = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/executor_drop_menu_auto_complete_text_view");
        executor.click();
        MobileElement el21 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.LinearLayout");
        el21.click();
        MobileElement date = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/date_in_plan_text_input_edit_text");
        date.click();
        MobileElement dateSaveBtn = (MobileElement) driver.findElementById("android:id/button1");
        dateSaveBtn.click();
        MobileElement time = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/time_in_plan_text_input_edit_text");
        time.click();
        MobileElement timeSaveBtn = (MobileElement) driver.findElementById("android:id/button1");
        timeSaveBtn.click();
        MobileElement description = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/description_edit_text");
        description.sendKeys("Test description");
        MobileElement saveBtn = (MobileElement) driver.findElementByAccessibilityId("Сохранить");
        saveBtn.click();
        MobileElement taskTitle = (MobileElement) driver.findElementById("ru.iteco.fmhandroid:id/title_material_text_view");
        taskTitle.isDisplayed();
    }


    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
