package com.mironouz.advanced.registration;

import com.mironouz.patterns.pageobject.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Dima on 6/7/2017.
 */
public class RegistrationPage extends PageObject{

    @FindBy(xpath="//div[@class='hero__register']//input[@name='fullname']")
    private WebElement fullname;

    @FindBy(xpath="//div[@class='hero__register']//input[@name='email']")
    private WebElement email;

    @FindBy(xpath="//div[@class='hero__register']//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//div[@class='hero__register']//input[@name='tos_agree']")
    private WebElement checkBox;

    @FindBy(xpath = "//div[@class='hero__register']//button[@type='submit']")
    private WebElement submitButton;

    RegistrationPage(WebDriver driver){
        super(driver);
    }

    public void enterFullName(String fullname){
        this.fullname.sendKeys(fullname);
    }

    public void enterEmail(String email){
        this.email.sendKeys(email);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
    }

    public void fillCheckbox(){
        checkBox.click();
    }

    public void submit(){
        submitButton.click();
    }
}
