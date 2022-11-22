package com.example;

import com.example.api.client.AuthRegisterClient;
import com.example.constants.Constants;
import com.example.page.MainPage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class BaseAuthUITest {

    private static String accessToken;

    protected static WebDriver driver;

    protected static MainPage mainPage;

    protected static User user;

    @BeforeClass
    public static void startUpBase() {
        createUser();
    }

    @Before
    public void setUpBase() {
        driver = DriverFactory.getBrowser(Constants.CHROME);
        //driver = DriverFactory.getBrowser(Constants.YANDEX);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.get(Constants.BASE_PATH);
        mainPage.waitSignInButton();
    }

    @AfterClass
    public static void afterClassBase() {
        removeUser();
    }

    @After
    public void tearDownBase() {
        driver.quit();
    }

    private static void createUser() {
        user = new User();
        accessToken = AuthRegisterClient.createUserBeforeTests(user);
    }

    private static void removeUser() {
        AuthRegisterClient.deleteUser(accessToken);
    }
}
