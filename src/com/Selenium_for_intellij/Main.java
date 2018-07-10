package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static WebDriver driver;
    static String browser;
    static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            setBrowser();
            if (setBrowserConfig()) {
                break;
            }
            continue;
        }
        getAllLinks();
        runTest();
        navigateToCareer();
        navigateToContact();
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

    public static void getAllLinks() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.iquantile.com/");
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("We have total " + allLinks.size() + " links in this website. The list is given below: ");
        for (WebElement links : allLinks) {
            System.out.println(links.getAttribute("href"));
        }

    }

    public static void runTest() throws InterruptedException {

        /**
         * Go to url, maximize window and match title of the page
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

        /**
         * find search bar and search iquantile
         */
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("iquantile");
        element.submit();

        /**
         * grab the result from the DOM and print it in console
         */
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        /**
         * these are the links we like to visit
         */
        System.out.println("Total " + findElements.size() + " search results are: ");
        for (WebElement webElement : findElements) {
            System.out.println(webElement.getAttribute("href"));
            //            webElement.getAttribute("href");
        }

        /**
         * navigate to the first result link
         */
        String first_link = findElements.get(0).getAttribute("href");
        System.out.print("Entering to: " + first_link + "\n");
        driver.navigate().to(first_link);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void navigateToCareer() {
        /**
         * go to career page
         */
        driver.navigate().to("https://www.iquantile.com/career");
        System.out.print("Career Page URL: ");
        String careerUrl = driver.getCurrentUrl();
        System.out.println(careerUrl);
        boolean careerPageText = driver.getPageSource().contains("WORK WITH US");
        if (careerPageText = true) {
            System.out.println("Career page verified !");
        } else {
            System.out.println("Somethis went wrong !");
        }
    }

    public static void navigateToContact() {
        /**
         * go to contact page and submit without filling the form
         */
        driver.navigate().to("https://www.iquantile.com/index.php#contactz");
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement element = driver.findElement(By.className("disabled"));
        element.submit();

        /**
         * Empty field error messages
         */
        List<WebElement> errorMessages = driver.findElements(By.className("list-unstyled"));
        System.out.println("Mandatory field(s) error message: ");
        for (WebElement error : errorMessages) {
            System.out.println(error.getText());
        }

        /**
         * Submit button error message
         */
        System.out.println("Submit button error message: ");
        WebElement buttonError = driver.findElement(By.id("c-form-submit"));
        System.out.println(buttonError.getText());
    }


    /**
     * quit the browser and clear the session
     */
    public static void tearDown() {
        driver.quit();
    }
}
