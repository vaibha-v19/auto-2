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
            // Navigate to the login page
            driver.get("https://omayo.blogspot.com/");

            // Maximize the browser window
            driver.manage().window().maximize();

            // Create an instance of WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(home.dropDown()));

            // Select options from the dropdown
            home.selectFromDropDown("Older Newsletters");

            // Create a Select object to interact with the dropdown
            Select dropdown = new Select(home.dropDown());

            // Verify the selected option
            String selectedOptionText = dropdown.getFirstSelectedOption().getText(); // Get the text of the selected option
            System.out.println("Selected option text: " + selectedOptionText); // Debugging output
            assertEquals(selectedOptionText, "Older Newsletters", "The selected option is not as expected!"); // Check against the expected text

            // Select additional options
            home.selectFromDropDown("doc 1");
            home.selectFromDropDown("doc 2");

            // Perform double-click action
            home.doubleClickButton();

            // Wait for the alert to be present and accept it
            wait.until(ExpectedConditions.alertIsPresent());
            String alertText = driver.switchTo().alert().getText(); // Get the alert text
            System.out.println("Alert text: " + alertText); // Debugging output
            driver.switchTo().alert().accept(); // Click OK on the alert

            // Verify the alert text
            assertEquals(alertText, "Double Click Successfull", "Alert text is not as expected!"); // Update to match the actual alert text

            // If you reach this point without exceptions, the test is successful
            System.out.println("Test executed successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred during the test execution: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
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
