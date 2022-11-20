package com.example;

import com.example.page.SignInPage;
import com.example.page.MainPage;
import com.example.page.AccountProfilePage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AccountProfilePageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getBrowser(Constants.CHROME);
        //driver = DriverFactory.getBrowser(Constants.YANDEX);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(Constants.BASE_PATH);
        mainPage = new MainPage(driver);
        mainPage.waitSignInButton();
        mainPage.clickSignInButton();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.waitSignInButton();
        signInPage.fillEmail(Constants.EMAIL);
        signInPage.fillPassword(Constants.PASSWORD);
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на кнопку \"Личный кабинет\"")
    public void testMoveToProfile() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        mainPage.clickAccountButton();
        accountProfilePage.waitAccountProfileLink();
        assertEquals(Constants.ACCOUNT_PROFILE_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на кнопку \"Конструктор\"")
    public void testMoveToConstructor() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        mainPage.clickAccountButton();
        accountProfilePage.waitAccountProfileLink();
        mainPage.clickConstructorLink();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void testMoveToMainPage() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        mainPage.clickAccountButton();
        accountProfilePage.waitAccountProfileLink();
        mainPage.clickLogo();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке \"Выйти\" в личном кабинете")
    public void testLogout() {
        AccountProfilePage accountProfilePage = new AccountProfilePage(driver);
        mainPage.clickAccountButton();
        accountProfilePage.waitAccountProfileLink();
        accountProfilePage.logoutClick();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.waitSignInButton();
        assertEquals(Constants.LOGIN_PATH, driver.getCurrentUrl());
    }
}