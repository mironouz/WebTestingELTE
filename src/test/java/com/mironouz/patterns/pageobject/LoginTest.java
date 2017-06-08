package com.mironouz.patterns.pageobject;

import com.mironouz.pages.LoginPage;
import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Dima on 6/7/2017.
 */
public class LoginTest {
    private WebDriver driver;
    private Properties properties = Utility.readProperties();;
    private String url = properties.getProperty("loginpage");
    private String fakeLogin = properties.getProperty("fakeLogin");
    private String fakePassword = properties.getProperty("fakePassword");

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void testAuthenticationFailureWhenProvidingBadCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(fakeLogin, fakePassword);
        loginPage.clickLogin();
        assertTrue(driver.getCurrentUrl().endsWith("login"));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
