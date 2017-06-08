package com.mironouz.basic;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.util.Properties;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 * Simple login test
 */
public class LoginTest {
    private WebDriver driver;
    private final Properties properties = Utility.readProperties();
    private final String url = properties.getProperty("url");
    private final String correctLogin = properties.getProperty("correctLogin");
    private final String correctPassword = properties.getProperty("correctPassword");
    private final String fakeLogin = properties.getProperty("fakeLogin");
    private final String fakePassword = properties.getProperty("fakePassword");

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials() throws TimeoutException {
        Utility.login(fakeLogin, fakePassword, driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBe(By.className("error-message"), "Invalid email or password"));
        assertFalse(driver.getCurrentUrl().endsWith("home"));
    }

    @Test
    public void testAuthenticationSuccessWhenProvidingCorrectCredentials() throws TimeoutException {
        Utility.login(correctLogin, correctPassword, driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("Files - Dropbox"));
        assertTrue(driver.getCurrentUrl().endsWith("home"));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
