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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(5000);
        click(By.xpath("//a[@href='/let-car-work']"));
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
//        "6/29/2025","6/31/2025"===> 29 & 31
        String[] from = dateFrom.split("/"); // [5][29][2025]  ---> from[1]
        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));

        String[] to = dateTo.split("/");
        String locatorTo = "//div[text()=' " + to[1] + " ']";
        click(By.xpath(locatorTo));
        submit();
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        pause(10000);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }


    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
//        "7/3/2025","11/15/2025"
        LocalDate now = LocalDate.now(); //2025-05-28

        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println(from);
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println(to);

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));


    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.xpath("//button[@aria-label = 'Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
//        "10/09/2025", "3/8/2026"
        LocalDate now = LocalDate.now();
        System.out.println(now);
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println(from);
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println(to);

        int difYearF = from.getYear() - year;
        int difMonthF;
        if (difYearF > 0) {
            difMonthF = (int) (from.getMonthValue()) + ((12) - now.getMonthValue());
            clickNextMonthBtn(difMonthF);
        } else {
            difMonthF = from.getMonthValue() - now.getMonthValue();
            clickNextMonthBtn(difMonthF);
        }

        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        int difYearT = to.getYear() - year;
        int diffMonthTo;
        if (difYearT > 0) {
            diffMonthTo = (int) (from.getMonthValue()) + ((12) - now.getMonthValue());
            clickNextMonthBtn(diffMonthTo);
        }
        diffMonthTo = to.getMonthValue() - from.getMonthValue();
        clickNextMonthBtn(diffMonthTo);

        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));
    }


}
