package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class iQuantilePortfolio {
    private WebDriver driver;

    public iQuantilePortfolio(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPortfolio() {
        String expectedPortfoliosUrl = "https://www.iquantile.com/index.php#portfolioz";
        WebElement element = driver.findElement(By.linkText("WHAT WE BUILT"));
        String actualPortfolioUrl = element.getAttribute("href");
        System.out.println("Portfolio Page URL: " + actualPortfolioUrl);
        driver.get(actualPortfolioUrl);
        if (actualPortfolioUrl.equals(expectedPortfoliosUrl)) {
            System.out.println("Portfolio Page is verified !");
        } else {
            System.out.println("Something went wrong with Portfolio Page !");
        }
    }
}
