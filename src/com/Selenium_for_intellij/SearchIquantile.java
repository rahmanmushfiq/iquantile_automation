package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchIquantile {

    private WebDriver driver;

    public SearchIquantile(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToGoogleAndSearchIquantile() {
        /*
          Go to url, maximize window and match title of the page
         */
        driver.navigate().to("https://google.com");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";
        if (actualTitle.equalsIgnoreCase(expectedTitle)) {
            System.out.println("Title Matched! ");

        } else {
            System.out.println("Title didn't Match!");
        }

        /*
          find search bar and search iquantile
         */
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("iquantile");
        element.submit();

        /*
         * grab the result from the DOM and print it in console
         */
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        /*
         * these are the links we like to visit
         */
        System.out.println("Total " + findElements.size() + " search results are: ");
        for (WebElement webElement : findElements) {
            System.out.println(webElement.getAttribute("href"));
            //            webElement.getAttribute("href");
        }

        /*
         * navigate to the first result link
         */
        String first_link = findElements.get(0).getAttribute("href");
        System.out.print("Entering to: " + first_link + "\n");
        driver.navigate().to(first_link);
    }
}
