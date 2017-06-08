package com.mironouz.advanced;

import com.mironouz.pages.RegistrationPage;
import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;


/**
 * Created by Dima on 6/7/2017.
 * Registration page
 */
public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private final Properties properties = Utility.readProperties();
    private final String url = properties.getProperty("url");
    private final String fullname = properties.getProperty("fullname");
    private final String wrongEmail = properties.getProperty("wrongEmail");
    private final String password = properties.getProperty("correctPassword");

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        registrationPage = new RegistrationPage(driver);
        registrationPage.enterFullName(fullname);
        registrationPage.enterPassword(password);
        registrationPage.fillCheckbox();
    }

    @Test
    public void testWrongEmail(){
        registrationPage.enterEmail(wrongEmail);
        registrationPage.submit();
        assertEquals(driver.findElement(By.className("register-form__error")).getText(),
                "The domain portion of the email address is invalid (the portion after the @: test)");
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
