package com.mironouz.basic;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 */
public class LoginTest {
    private WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://dropbox.com");
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials() throws TimeoutException {
        Utility.login("fakeuser@mail.ru", "fakepassword", driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBe(By.className("error-message"), "Invalid email or password"));
        assertFalse(driver.getCurrentUrl().endsWith("home"));
    }

    @Test
    public void testAuthenticationSuccessWhenProvidingCorrectCredentials() throws TimeoutException {
        Utility.login("WebTestingElte@mail.ru", "Elte2016", driver);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("Files - Dropbox"));
        assertTrue(driver.getCurrentUrl().endsWith("home"));
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
