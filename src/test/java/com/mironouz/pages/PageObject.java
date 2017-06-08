package com.mironouz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Dima on 6/7/2017.
 * Parent for all Page Objects
 */

public class PageObject {
    protected final WebDriver driver;
    PageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
