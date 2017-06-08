package com.mironouz.basic;

import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dima on 6/7/2017.
 * tests for static pages
 */
public class StaticPagesTest {
    private WebDriver driver;
    private Properties properties = Utility.readProperties();
    private final String aboutUrl = properties.getProperty("static.about");
    private final String contactUrl = properties.getProperty("static.contact");
    private final String brandingUrl = properties.getProperty("static.branding");

    @Before
    public void setup(){
        properties = Utility.readProperties();
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testAboutPage(){
        driver.get(aboutUrl);
        WebElement about = driver.findElement(By.className("intro-text"));
        assertEquals(about.getText(), "Dropbox is a technology company that builds simple, powerful products for people and businesses.");
    }

    @Test
    public void testContactPage(){
        driver.get(contactUrl);
        assertEquals(driver.getTitle(), "Contact - Dropbox");
    }

    @Test
    public void testBrandingPage()
    {
        driver.get(brandingUrl);
        assertEquals(driver.getTitle(), "Logo & Branding - Dropbox");
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
