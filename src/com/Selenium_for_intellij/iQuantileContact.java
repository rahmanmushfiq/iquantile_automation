package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class iQuantileContact {
    private WebDriver driver;

    public iQuantileContact(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToContactPage() {
        /*
         * go to contact page and submit without filling the form
         */
        String expectedContactUrl = "https://www.iquantile.com/index.php#contactz";
        WebElement element = driver.findElement(By.linkText("CONTACT"));
        System.out.print("Contact Page URL: ");
        String actualContactUrl = element.getAttribute("href");
        System.out.println(actualContactUrl);
        driver.get(actualContactUrl);
        if (actualContactUrl.equals(expectedContactUrl)) {
            System.out.println("Contact Page is verified !");
        } else {
            System.out.println("Something went wrong with Contact Page !");
        }
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        element = driver.findElement(By.className("disabled"));
        element.submit();

        /*
         * Empty field error messages
         */
        List<WebElement> errorMessages = driver.findElements(By.className("list-unstyled"));
        System.out.println("Mandatory field(s) error message: ");
        for (WebElement error : errorMessages) {
            System.out.println(error.getText());
        }

        /*
         * Submit button error message
         */
        System.out.println("Submit button error message: ");
        WebElement buttonError = driver.findElement(By.id("c-form-submit"));
        System.out.println(buttonError.getText());
    }
}
