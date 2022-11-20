package com.example;

import com.example.page.SignInPage;
import com.example.page.MainPage;
import com.example.page.PasswordRecoveryPage;
import com.example.page.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SignInTest {

    private SignInPage signInPage;

    private WebDriver driver;

    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getBrowser(Constants.CHROME);
        //driver = DriverFactory.getBrowser(Constants.YANDEX);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        driver.get(Constants.BASE_PATH);
        mainPage.waitSignInButton();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной")
    public void testSignInByButtonOnMainPage() {
        signInPage = new SignInPage(driver);
        mainPage.clickSignInButton();
        signInPage.waitSignInButton();
        signInPage.fillEmail(Constants.EMAIL);
        signInPage.fillPassword(Constants.PASSWORD);
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    public void testSignInByAccountProfileButtonOnMainPage() {
        signInPage = new SignInPage(driver);
        mainPage.clickAccountButton();
        signInPage.waitSignInButton();
        signInPage.fillEmail(Constants.EMAIL);
        signInPage.fillPassword(Constants.PASSWORD);
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testSignInByButtonOnRegistrationForm() {
        signInPage = new SignInPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        driver.get(Constants.REGISTER_PATH);
        registerPage.waitRegisterButton();
        registerPage.clickSignInButton();
        signInPage.waitSignInButton();
        signInPage.fillEmail(Constants.EMAIL);
        signInPage.fillPassword(Constants.PASSWORD);
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testSignInByButtonOnPasswordResetForm() {
        signInPage = new SignInPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        driver.get(Constants.FORGOT_PASSWORD_PATH);
        passwordRecoveryPage.waitRecoveryPasswordButton();
        passwordRecoveryPage.clickSignInLink();
        signInPage.waitSignInButton();
        signInPage.fillEmail(Constants.EMAIL);
        signInPage.fillPassword(Constants.PASSWORD);
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }
}
