package com.mironouz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Dima on 6/7/2017.
 * Page Object for homepage
 */



public class HomePage extends PageObject {

    @FindBy(xpath = "//div/main/div[2]/div/div[1]/div[2]/div/div/button/span/span")
    private WebElement uploadFilesButton;

    @FindBy(className = "basic-uploader-link")
    private WebElement basicUploaderButton;

    @FindBy(xpath = "//*[@id=\"basic-upload-form\"]/input[5]")
    private WebElement fileForm;

    @FindBy(className = "is-sharedwith-column-visible")
    private WebElement allFiles;

    @FindBy(xpath = "//div/main/div[2]/div/div[1]/div[2]/ul/li[7]/button/span/span[2]/div")
    private WebElement deleteButton;

    @FindBy(className = "button-primary")
    private WebElement acceptDeleteButton;

    @FindBy(className = "primary-action-menu__button")
    private WebElement downloadButton;

    HomePage(WebDriver driver){
        super(driver);
    }

    public void uploadFile(String path){
        uploadFilesButton.click();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(basicUploaderButton)).click();
        fileForm.sendKeys(path);
    }

    public int countOfFiles(){
        WebElement table = new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(allFiles));
        List<WebElement> files = table.findElements(By.tagName("li"));
        return files.size();
    }

    public void deleteChoosenFile(){
        deleteButton.click();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(acceptDeleteButton));
        acceptDeleteButton.click();
    }

    private void chooseFile(int num){
        driver.findElement(By.xpath("//div[2]/ul/li[" + num + "]//button")).click();
    }

    public void downloadAllFiles(){
        for(int i = 1; i <= countOfFiles(); i++) chooseFile(i);
        downloadButton.click();
    }
}
