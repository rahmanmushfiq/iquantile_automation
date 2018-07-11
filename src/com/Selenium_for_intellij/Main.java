package com.Selenium_for_intellij;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;

public class Main {
    static WebDriver driver;
    static String browser;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            setBrowser();
            if (setBrowserConfig()) {
                break;
            }
            continue;
        }

        new AllLinks(driver).getAllLinks();
        new SearchIquantile(driver).navigateToGoogleAndSearchIquantile();
        new iQuantileHome(driver).navigateToHome();
        new iQuantileServices(driver).navigateToServices();
        new iQuantilePortfolio(driver).navigateToPortfolio();
        new iQuantileAbout(driver).navigateToAbout();
        new iQuantileContact(driver).navigateToContactPage();
        new iQuantileCareer(driver).navigateToCareerPage();

        tearDown();
    }

    public static void setBrowser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which browser do you want to use ? ");
        browser = sc.nextLine();
    }

    public static boolean setBrowserConfig() {

        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "E:\\Workspace\\Selenium Test\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            return true;
        } else if (browser.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver",
                    "E:\\Workspace\\Selenium Test\\drivers\\MicrosoftWebDriver.exe");
            driver = new EdgeDriver();
            return true;
        } else if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "E:\\Workspace\\Selenium Test\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            return true;
        }
        System.out.println("You have entered an invalid browser name !");
        return false;
    }

    /*
     * quit the browser and clear the session
     */
    public static void tearDown() {
        driver.quit();
    }
}
