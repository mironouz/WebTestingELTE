package com.mironouz.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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

    public static DesiredCapabilities customDownloadPath(String path){
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", path);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        return cap;
    }
}
