package com.mironouz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Dima on 6/7/2017.
 */
public class LoginPage extends PageObject {
    @FindBy(className="text-input-input")
    private WebElement login;

    @FindBy(className="password-input")
    private WebElement password;

    @FindBy(className="login-button")
    private WebElement loginButton;

    public  LoginPage(WebDriver driver){
        super(driver);
    }

    public void enterCredentials(String login, String password){
        this.login.sendKeys(login);
        this.password.sendKeys(password);
    }

    public HomePage login(){
        loginButton.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs("Files - Dropbox"));
        return new HomePage(driver);
    }
}
