package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

class AllLinks {
    private WebDriver driver;

    public AllLinks(WebDriver driver) {
        this.driver = driver;
    }

    public void getAllLinks() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.iquantile.com/");
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("We have total " + allLinks.size() + " links in this website. The list is given below: ");
        for (WebElement links : allLinks) {
            System.out.println(links.getAttribute("href"));
        }
    }
}
