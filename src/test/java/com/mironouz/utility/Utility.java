package com.mironouz.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Dima on 6/7/2017.
 */
public class Utility {
    public static void login(String login, String password, WebDriver driver){
        driver.findElement(By.id("sign-in")).click();
        driver.findElement(By.className("text-input-input")).sendKeys(login);
        driver.findElement(By.className("password-input")).sendKeys(password);
        driver.findElement(By.className("login-button")).click();
    }
}
