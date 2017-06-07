package com.mironouz.advanced;

import com.mironouz.patterns.pageobject.LoginPage;
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
    private Properties properties = Utility.readProperties();;
    private String url = properties.getProperty("loginpage");
    private String correctLogin = properties.getProperty("correctLogin");
    private String correctPassword = properties.getProperty("correctPassword");

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void testFileUpload(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(correctLogin, correctPassword);
        loginPage.login();
        WebElement uploadFilesButton = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/main/div[2]/div/div[1]/div[2]/div/div/button/span/span")));
        uploadFilesButton.click();
        driver.findElement(By.className("basic-uploader-link")).click();
        driver.findElement(By.xpath("//*[@id=\"basic-upload-form\"]/input[5]")).sendKeys("C:\\Users\\Dima\\IdeaProjects\\WebTestingELTE\\src\\config.properties");
        WebElement table = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.className("is-sharedwith-column-visible")));
        List<WebElement> files = table.findElements(By.tagName("li"));
        assertEquals(files.size(), 3);
    }

    @After
    public void tearDown(){
        driver.findElement(By.xpath("//div/main/div[2]/div/div[1]/div[2]/ul/li[7]/button/span/span[2]/div")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.className("button-primary"))).click();
        driver.close();
    }

}
