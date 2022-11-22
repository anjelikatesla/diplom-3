package com.example;

import com.example.api.client.AuthLoginClient;
import com.example.api.client.AuthRegisterClient;
import com.example.constants.Constants;
import com.example.page.RegisterPage;
import com.example.page.SignInPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseUITest {

    private RegisterPage registerPage;

    private User user;

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
        user = new User();
        registerPage.fillName(user.getName());
        registerPage.fillEmail(user.getEmail());
        registerPage.fillPassword(user.getPassword());
        registerPage.clickRegisterButton();
        signInPage.waitSignInButton();
        assertEquals(Constants.LOGIN_PATH, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Регистрация с неправильным паролем")
    public void testRegistrationWithWrongPassword() {
        String name = RandomStringUtils.randomAlphabetic(8);
        String email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphanumeric(5);
        user = new User(name, password, email);
        registerPage.fillName(name);
        registerPage.fillEmail(email);
        registerPage.fillPassword(password);
        registerPage.clickRegisterButton();
        registerPage.waitErrorText();
        assertEquals("Некорректный пароль", registerPage.getErrorText());
    }

    @After
    public void tearDown() {
        removeUser(user);
    }

    private void removeUser(User user) {
        System.out.println(user.getEmail() + " " + user.getPassword());
        String accessToken = AuthLoginClient.getUserToken(user);
        if (accessToken != null) {
            AuthRegisterClient.deleteUser(accessToken);
        }
    }
}