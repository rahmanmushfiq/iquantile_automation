package com.Selenium_for_intellij;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Scanner;

public class Main {
    static WebDriver driver;
    static String browser;
    static String projectPath = System.getProperty("user.dir");
    static String chromeDriverPath = projectPath + "\\drivers\\chromedriver.exe";
    static String geckoDriverPath = projectPath + "\\drivers\\geckodriver.exe";
    static String edgeDriverPath = projectPath + "\\drivers\\MicrosoftWebDriver.exe";
//    static final String chromeDriver = "G:\\CS Works\\Selenium Projects\\iquantile_automation\\drivers\\chromedriver.exe";
//    static final String firefoxDriver = "G:\\CS Works\\Selenium Projects\\iquantile_automation\\drivers\\geckodriver.exe";
//    static final String edgeDriver = "G:\\CS Works\\Selenium Projects\\iquantile_automation\\drivers\\MicrosoftWebDriver.exe";

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
        Scanner input = new Scanner(System.in);
        System.out.println("Which browser do you want to use ? ");
        browser = input.nextLine();
    }

    public static boolean setBrowserConfig() {

        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
            return true;
        } else if (browser.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", edgeDriverPath);
            driver = new EdgeDriver();
            return true;
        } else if (browser.equalsIgnoreCase("Firefox")) {

            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
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
        System.out.println(browser + " is Closing....");
        if (browser.equalsIgnoreCase("Firefox")) {
            driver.quit();
        } else {
            driver.close();
            driver.quit();
        }
    }
}
