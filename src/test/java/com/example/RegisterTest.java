package com.example;

import com.example.page.SignInPage;
import com.example.page.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseUITest {

    private RegisterPage registerPage;

    private String email;

    private String password;

    @Before
    public void setUp() {
        registerPage = new RegisterPage(driver);
        driver.get(Constants.REGISTER_PATH);
        driver.manage().window().maximize();
        registerPage.waitRegisterButton();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void testSuccessRegistration() {
        SignInPage signInPage = new SignInPage(driver);
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(10);
        registerPage.fillName(Constants.NAME);
        registerPage.fillEmail(email);
        registerPage.fillPassword(password);
        registerPage.clickRegisterButton();
        signInPage.waitSignInButton();
        assertEquals(Constants.LOGIN_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Регистрация с неправильным паролем")
    public void testRegistrationWithWrongPassword() {
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(5);
        registerPage.fillName(Constants.NAME);
        registerPage.fillEmail(email);
        registerPage.fillPassword(password);
        registerPage.clickRegisterButton();
        registerPage.waitErrorText();
        assertEquals("Некорректный пароль", registerPage.getErrorText());
    }
}