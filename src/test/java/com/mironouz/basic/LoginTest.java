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


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 */
public class LoginTest {
    private WebDriver driver;
    private Properties properties;

    @Before
    public void setup() throws IOException {
        FileInputStream inputStream  = new FileInputStream("src/config.properties");
        properties = new Properties();
        properties.load(inputStream);
        inputStream.close();
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(properties.getProperty("url"));
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials() throws TimeoutException {
        Utility.login(properties.getProperty("fakeLogin"), properties.getProperty("fakePassword"), driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBe(By.className("error-message"), "Invalid email or password"));
        assertFalse(driver.getCurrentUrl().endsWith("home"));
    }

    @Test
    public void testAuthenticationSuccessWhenProvidingCorrectCredentials() throws TimeoutException {
        Utility.login(properties.getProperty("correctLogin"), properties.getProperty("correctPassword"), driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("Files - Dropbox"));
        assertTrue(driver.getCurrentUrl().endsWith("home"));
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
