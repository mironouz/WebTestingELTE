package com.mironouz.patterns.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dima on 6/7/2017.
 */
public class LoginPage extends PageObject{
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

    public void login(){
        loginButton.click();
    }
}
