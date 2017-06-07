package com.mironouz.patterns.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dima on 6/7/2017.
 */

public class PageObject {
    protected WebDriver driver;
    public PageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
