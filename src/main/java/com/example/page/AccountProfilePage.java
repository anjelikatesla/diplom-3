package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountProfilePage {

    private final WebDriver driver;

    private final By profileLink = By.linkText("Профиль");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitAccountProfileLink() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileLink));
    }

    public void logoutClick() {
        WebElement element = driver.findElement(logoutButton);
        element.click();
    }
}