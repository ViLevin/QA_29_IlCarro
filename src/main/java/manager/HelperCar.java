package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(5000);
        click(By.xpath("//a[@href='let-car-work']"));
    }

    public void fillCarForm(Car car) {

//        typeLocation(car.getLocation());
        if (car.getLocation() != null && !car.getLocation().trim().isEmpty()) {
            typeLocation(car.getLocation());
        }

        type(By.id("make"), car.getManufactura());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
//        type(By.id("price"), String.valueOf(car.getPrice())); // = >>> String 1
        type(By.id("price"), car.getPrice() + "");// = >>> String 2
//        type(By.id("pre"), car.getPrice() + "");// = >>> String 2
        type(By.id("about"), car.getAbout());


    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
//                Gas:
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.deselectByVisibleText(" Gas ");


    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
//        type(By.id("pickUpPlace"), location);
//        click(By.cssSelector("div.pac-item"));
//        if (location != null && !location.trim().isEmpty()) {
//            try {
//                WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
//                WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pac-item")));
//                suggestion.click();
//            } catch (TimeoutException e) {
//                System.out.println("⚠ No autocomplete suggestion appeared.");
//            }
//        }
    }


    public void returnSuccessToHomePage() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void returnUnsuccessToHomePage() {
        click(By.xpath("//*[@id='0']"));
    }

    public void attachPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }
}
