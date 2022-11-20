package com.example.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {

    WebDriver driver;

    public final By signInButton = By.xpath(".//button[text()='Войти']");

    private final By emailInputField = By.xpath(".//fieldset[1]//input");

    private final By passwordInputField = By.xpath(".//fieldset[2]//input");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitSignInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }

    public void fillEmail(String email) {
        WebElement element = driver.findElement(emailInputField);
        element.sendKeys(email);
    }

    public void fillPassword(String password) {
        WebElement element = driver.findElement(passwordInputField);
        element.sendKeys(password);
    }
    public void clickSignInButton() {
        WebElement element = driver.findElement(signInButton);
        element.click();
    }
}