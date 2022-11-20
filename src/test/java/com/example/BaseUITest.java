package com.example;

import com.example.page.MainPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseUITest {

    protected static WebDriver driver;
    protected static MainPage mainPage;

    @BeforeClass
    public static void startUp() {
        driver = DriverFactory.getBrowser(Constants.CHROME);
        //driver = DriverFactory.getBrowser(Constants.YANDEX);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.get(Constants.BASE_PATH);
    }

    @AfterClass
    public static void afterTest() {
        driver.quit();
    }
}
