package com.example.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {

    WebDriver driver;

    public final By recoveryPasswordButton = By.xpath(".//button[text()='Восстановить']");

    public final By signInLink = By.linkText("Войти");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitRecoveryPasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(recoveryPasswordButton));
    }

    public void clickSignInLink() {
        WebElement element = driver.findElement(signInLink);
        element.click();
    }
}