package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class iQuantileAbout {
    private WebDriver driver;

    public iQuantileAbout(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToAbout() {
        String expectedAboutsUrl = "https://www.iquantile.com/index.php#aboutz";
        WebElement element = driver.findElement(By.linkText("WHO WE ARE"));
        String actualAboutUrl = element.getAttribute("href");
        System.out.println("About Page URL: " + actualAboutUrl);
        driver.get(actualAboutUrl);
        if (actualAboutUrl.equals(expectedAboutsUrl)) {
            System.out.println("Service Page is verified !");
        } else {
            System.out.println("Something went wrong with About Page !");
        }
    }
}