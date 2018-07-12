package com.Selenium_for_intellij;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;

public class Main {
    static WebDriver driver;
    static String browser;
    static final String chromeDriver = "E:\\Workspace\\Selenium Test\\drivers\\chromedriver.exe";
    static final String firefoxDriver = "E:\\Workspace\\Selenium Test\\drivers\\geckodriver.exe";
    static final String edgeDriver = "E:\\Workspace\\Selenium Test\\drivers\\MicrosoftWebDriver.exe";

    public static void main(String[] args) {

        while (true) {
            setBrowser();
            if (setBrowserConfig()) {
                break;
            }
            continue;
        }

        BrokenLinks brokenLinks = new BrokenLinks(driver);
        brokenLinks.findAllLinks();
        brokenLinks.checkLinks();
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
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            driver = new ChromeDriver();
            return true;
        } else if (browser.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", edgeDriver);
            driver = new EdgeDriver();
            return true;
        } else if (browser.equalsIgnoreCase("Firefox")) {

            System.setProperty("webdriver.gecko.driver", firefoxDriver);
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

        System.out.println("Closing .......");
        driver.quit();
    }
}
