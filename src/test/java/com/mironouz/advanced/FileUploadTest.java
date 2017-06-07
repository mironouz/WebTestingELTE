package com.mironouz.advanced;

import com.mironouz.pages.HomePage;
import com.mironouz.pages.LoginPage;
import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dima on 6/7/2017.
 */
public class FileUploadTest{
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private Properties properties = Utility.readProperties();;
    private String url = properties.getProperty("loginpage");
    private String correctLogin = properties.getProperty("correctLogin");
    private String correctPassword = properties.getProperty("correctPassword");

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(correctLogin, correctPassword);
        homePage = loginPage.login();
    }

    @Test
    public void testFileUpload(){
        homePage.uploadFile("C:\\Users\\Dima\\IdeaProjects\\WebTestingELTE\\src\\config.properties");
        assertEquals(homePage.countOfFiles(), 3);
    }

    @After
    public void tearDown(){
        homePage.deleteChoosenFile();
        driver.close();
    }

}
