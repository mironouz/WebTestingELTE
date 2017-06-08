package com.mironouz.patterns.actionbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dima on 6/7/2017.
 * Action bot pattern
 */
class ActionBot {
    private final WebDriver driver;

    ActionBot(WebDriver driver) {
        this.driver = driver;
    }

    void click(By locator) {
        driver.findElement(locator).click();
    }

    void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text + "\n");
    }
}
