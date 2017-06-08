package com.mironouz.basic;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 * Logout test
 */
public class LogoutTest {
    private WebDriver driver;
    private final Properties properties = Utility.readProperties();
    private final String url = properties.getProperty("url");
    private final String correctLogin = properties.getProperty("correctLogin");
    private final String correctPassword = properties.getProperty("correctPassword");

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void testLogout(){
        Utility.login(correctLogin, correctPassword, driver);

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(1, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement smile = wait.until(driver -> driver.findElement(By.className("mc-avatar-image")));

        smile.click();
        driver.findElement(By.xpath("//a[@href='https://www.dropbox.com/logout']")).click();
        assertTrue(driver.getCurrentUrl().endsWith("logout"));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
