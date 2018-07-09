package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class Main {

    static WebDriver driver;
    static String browser;
    static WebElement element;

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            setBrowser();
            if (setBrowserConfig()) {
                break;
            }
            continue;
        }
        runTest();
        navigateToContact();
        tearDown();
    }

    // Set Browser
    // Set Browser Config
    // Run Test
    // Navigate to Contact
    // Tear Down
    public static void setBrowser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which brower do you want to use ? ");
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
        }
        System.out.println("You have entered an invalid browser name !");
        return false;
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
        element = driver.findElement(By.name("q"));
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
        System.out.println("Results are: ");
        for (WebElement webElement : findElements) {
            System.out.println(webElement.getAttribute("href"));
//            webElement.getAttribute("href");
        }

        /**
         * navigate to the first result link
         */
        String first_link = findElements.get(0).getAttribute("href");
        driver.navigate().to(first_link);
//        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * go to career page
         */
        driver.navigate().to("https://www.iquantile.com/career");
        System.out.println("Career Page URL: ");
        String careerUrl = driver.getCurrentUrl();
        System.out.println(careerUrl);
        boolean careerPageText = driver.getPageSource().contains("WORK WITH US");
        if (careerPageText = true) {
            System.out.println("This is career page !");
        } else {
            System.out.println("Somethis is wrong !");
        }
    }

    public static void navigateToContact() {
        /**
         * go to contact page and submit without filling the form
         */
        driver.navigate().to("https://www.iquantile.com/index.php#contactz");
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        element = driver.findElement(By.className("disabled"));
        element.submit();

        /**
         * Empty field error messages
         */
        List<WebElement> errorMessages = driver.findElements(By.className("list-unstyled"));
        for (WebElement error : errorMessages) {
            System.out.println(error.getText());
        }

        /**
         * Submit button error message
         */
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
