package com.example;

import com.example.constants.Constants;
import com.example.page.PasswordRecoveryPage;
import com.example.page.RegisterPage;
import com.example.page.SignInPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignInTest extends BaseAuthUITest {

    private SignInPage signInPage;

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной")
    public void testSignInByButtonOnMainPage() {
        signInPage = new SignInPage(driver);
        mainPage.clickSignInButton();
        signInPage.waitSignInButton();
        signInPage.fillEmail(user.getEmail());
        signInPage.fillPassword(user.getPassword());
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
        signInPage.fillEmail(user.getEmail());
        signInPage.fillPassword(user.getPassword());
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
        signInPage.fillEmail(user.getEmail());
        signInPage.fillPassword(user.getPassword());
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
        signInPage.fillEmail(user.getEmail());
        signInPage.fillPassword(user.getPassword());
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
        assertEquals(Constants.BASE_PATH, driver.getCurrentUrl());
    }
}
