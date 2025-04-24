package objRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Home {
    WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for the dropdown
    private By dropDown = By.id("drop1");

    // Locator for the double-click button
    private By doubleClickButton = By.xpath("//button[normalize-space()='Double click Here']");

    // Method to get the dropdown element
    public WebElement dropDown() {
        return driver.findElement(dropDown);
    }

    // Method to select an option from the dropdown
    public void selectFromDropDown(String visibleText) {
        Select dropdown = new Select(dropDown());
        dropdown.selectByVisibleText(visibleText);
    }

    // Method to perform double-click on the button
    public void doubleClickButton() {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(doubleClickButton)).perform();
    }
}