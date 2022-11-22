package com.example;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseUITest {

    @Test
    @DisplayName("Переход к разделу \"Булки\"")
    public void testSwitchToBunsTab() {
        mainPage.clickBuns();
        assertEquals("Булки", mainPage.getTextActiveTab());
    }

    @Test
    @DisplayName("Переход в раздел \"Соусы\"")
    public void testSwitchToSaucesTab() {
        mainPage.clickSauces();
        assertEquals("Соусы", mainPage.getTextActiveTab());
    }

    @Test
    @DisplayName("Переход в раздел \"Начинки\"")
    public void testSwitchToFillingsTab() {
        mainPage.clickFillings();
        assertEquals("Начинки", mainPage.getTextActiveTab());
    }
}