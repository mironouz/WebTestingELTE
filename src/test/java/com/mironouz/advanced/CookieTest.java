package com.mironouz.advanced;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dima on 6/7/2017.
 */
public class CookieTest {
    private WebDriver driver;
    private Properties properties = Utility.readProperties();;
    private String url = properties.getProperty("url");

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void testAddCookie(){
        Cookie cookie = driver.manage().getCookieNamed("myCookie");
        assertEquals(cookie, null);
        Set<Cookie> cookies = driver.manage().getCookies();
        int initial_size = cookies.size();
        Cookie myCookie = new Cookie("myCookie", "myValue");
        driver.manage().addCookie(myCookie);
        cookie = driver.manage().getCookieNamed("myCookie");
        assertEquals(cookie.getValue(), "myValue");
        assertEquals(driver.manage().getCookies().size(), initial_size + 1);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
