package com.mironouz.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

    public static Properties readProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream inputStream  = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
