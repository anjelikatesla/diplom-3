package com.example.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By nameInputField = By.xpath(".//fieldset[1]//input");

    private final By emailInputField = By.xpath(".//fieldset[2]//input");

    private final By passwordInputField = By.xpath(".//fieldset[3]//input");

    private final By passwordErrorText = By.xpath(".//p[text()='Некорректный пароль']");

    private final By signInButton = By.linkText("Войти");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitRegisterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    public void fillName(String name) {
        WebElement element = driver.findElement(nameInputField);
        element.sendKeys(name);
    }

    public void fillEmail(String email) {
        WebElement element = driver.findElement(emailInputField);
        element.sendKeys(email);
    }

    public void fillPassword(String password) {
        WebElement element = driver.findElement(passwordInputField);
        element.sendKeys(password);
    }

    public void clickRegisterButton() {
        WebElement element = driver.findElement(registerButton);
        element.click();
    }

    public void waitErrorText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordErrorText));
    }

    public String getErrorText() {
        WebElement element = driver.findElement(passwordErrorText);
        return element.getText();
    }

    public void clickSignInButton() {
        WebElement element = driver.findElement(signInButton);
        element.click();
    }
}