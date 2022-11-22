package com.example;

import com.example.constants.Constants;
import com.example.page.AccountProfilePage;
import com.example.page.SignInPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountProfilePageTest extends BaseAuthUITest {

    @Before
    public void setUp() {
        mainPage.clickSignInButton();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.waitSignInButton();
        signInPage.fillEmail(user.getEmail());
        signInPage.fillPassword(user.getPassword());
        signInPage.clickSignInButton();
        mainPage.waitCreateOrderButton();
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