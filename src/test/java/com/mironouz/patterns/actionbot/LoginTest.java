package com.mironouz.patterns.actionbot;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 * Action bot pattern Login Test
 */
public class LoginTest {
    private WebDriver driver;
    private final Properties properties = Utility.readProperties();
    private final String url = properties.getProperty("loginpage");
    private final String fakeLogin = properties.getProperty("fakeLogin");
    private final String fakePassword = properties.getProperty("fakePassword");

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials(){
        ActionBot actionBot = new ActionBot(driver);
        actionBot.type(By.className("text-input-input"), fakeLogin);
        actionBot.type(By.className("password-input"), fakePassword);
        actionBot.click(By.className("login-button"));
        assertTrue(driver.getCurrentUrl().endsWith("login"));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
