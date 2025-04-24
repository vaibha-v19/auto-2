package mPackage;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import objRepo.Home;

public class AppTest {
    private WebDriver driver;
    private Home home;

    @BeforeClass
    public void setup() {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        home = new Home(driver); // Initialize the Home object
    }

    @Test
public void SampleAutomation() {
    try {
        System.out.println("Navigating to the website...");
        driver.get("https://omayo.blogspot.com/");
        driver.manage().window().maximize();

        System.out.println("Waiting for the dropdown to be visible...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(home.dropDown()));

        System.out.println("Selecting 'Older Newsletters' from the dropdown...");
        home.selectFromDropDown("Older Newsletters");

        Select dropdown = new Select(home.dropDown());
        String selectedOptionText = dropdown.getFirstSelectedOption().getText();
        System.out.println("Selected option text: " + selectedOptionText);
        assertEquals(selectedOptionText, "Older Newsletters", "The selected option is not as expected!");

        System.out.println("Selecting additional options...");
        home.selectFromDropDown("doc 1");
        home.selectFromDropDown("doc 2");

        System.out.println("Performing double-click action...");
        home.doubleClickButton();

        System.out.println("Waiting for the alert to be present...");
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text: " + alertText);
        driver.switchTo().alert().accept();

        assertEquals(alertText, "Double Click Successful", "Alert text is not as expected!");

        System.out.println("Test executed successfully.");
    } catch (Exception e) {
        System.err.println("An error occurred during the test execution: " + e.getMessage());
        e.printStackTrace();
    }
}
    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
