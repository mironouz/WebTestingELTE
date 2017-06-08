package com.mironouz.advanced;

import com.mironouz.pages.HomePage;
import com.mironouz.pages.LoginPage;
import com.mironouz.utility.Utility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dima on 6/7/2017.
 */
public class FileUploadDownloadTest {
    private WebDriver driver;
    private HomePage homePage;
    private Properties properties = Utility.readProperties();;
    private String url = properties.getProperty("loginpage");
    private String correctLogin = properties.getProperty("correctLogin");
    private String correctPassword = properties.getProperty("correctPassword");
    private String downloadFilepath = System.getProperty("user.dir");
    private File destination = new File(downloadFilepath);

    @Before
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        driver = new ChromeDriver(Utility.customDownloadPath(downloadFilepath));
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(correctLogin, correctPassword);
        homePage = loginPage.login();
    }

    @Test
    public void testFileUpload(){
        homePage.uploadFile("C:\\Users\\Dima\\IdeaProjects\\WebTestingELTE\\src\\config.properties");
        assertEquals(homePage.countOfFiles(), 3);
        homePage.deleteChoosenFile();
    }

    @Test
    public void testMultipleFilesDownload() throws InterruptedException, IOException {
        homePage.downloadAllFiles();
        do {
            Thread.sleep(5000L);
        } while(!org.apache.commons.io.FileUtils.listFiles(destination, new String[]{"crdownload"}, false).isEmpty());
        File file = new File(downloadFilepath + "\\Dropbox.zip");
        ZipFile zipFile = new ZipFile(file);
        assertEquals(zipFile.size(), homePage.countOfFiles());
        zipFile.close();
        Files.delete(file.toPath());
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
