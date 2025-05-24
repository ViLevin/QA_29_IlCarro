package tests;

import manager.HelperCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddNewCarUnsubmit extends TestBase {

    @BeforeClass
//    login app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
        }
    }

    @Test //discription - NO error message
    public void addNewCarUnsuccessLocation() {
        logger.info("Start");
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        Car car = Car.builder()
                .location("")
                .manufactura("Mazda")
                .model("Ma3shka")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

//        Assert.assertEquals("", app.getHelperUser().getErrorText());      ---> bug? NO error message
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }


    @Test
    public void addNewCarUnsuccessManufacture() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("")
                .model("Ma3shka")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();


        Assert.assertEquals("Make is required", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }

    @Test
    public void addNewCarUnsuccessModel() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();


        Assert.assertEquals("Model is required", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }

    @Test
    public void addNewCarUnsuccessYear() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("Ma3shka")
                .year("")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals("Year required", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }

    @Test(enabled = false)
    public void addNewCarUnsuccessFuel() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("Ma3shka")
                .year("2022")
                .fuel("")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals("Fuel is required", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }

    @Test
    public void addNewCarUnsuccessSeats() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("Ma3shka")
                .year("2022")
                .fuel("Gas")
                .seats(0)
                .carClass("C")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals("Car must have min 2 seat", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }

    @Test
    public void addNewCarUnsuccessCarClass() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("Ma3shka")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("")
                .carRegNumber("678-900-000")
                .price(50.)
                .about("Very nice car")
                .build();
        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals("Car class is required", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }

    @Test
    public void addNewCarUnsuccessCarRegNum() {
        logger.info("Start");
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("Ma3shka")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertEquals("Car registration number is required", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }


    @AfterMethod
    public void PostCondition() {
        app.getHelperCar().returnUnsuccessToHomePage();
    }

}
//@Test
//public void addNewCarUnsuccessLocationClear() {
//    Car car = Car.builder()
//            .location("")
//            .manufactura("Mazda")
//            .model("M3")
//            .year("2022")
//            .fuel("Petrol")
//            .seats(4)
//            .carClass("C")
//            .carRegNumber("678-900-77-101")
//            .price(50.)
//            .about("Very nice car")
//            .build();
//
//    app.getHelperCar().openCarForm();
//
//    WebDriver wd = app.getDriver();
//    WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
//
//    WebElement pickUpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickUpPlace")));
//
//    pickUpInput = wd.findElement(By.id("pickUpPlace"));
//    pickUpInput.sendKeys("Tel Aviv, Israel");
//
//    try {
//        WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.pac-item")));
//        suggestion.click();
//    } catch (TimeoutException e) {
//        System.out.println("⚠ div.pac-item not shown — autocomplete failed or blocked");
//    }
//
//    pickUpInput.clear();
//
//    app.getHelperCar().fillCarForm(car);
//    app.getHelperCar().submit();
//    Assert.assertEquals("Wrong address", app.getHelperUser().getErrorText());
//    Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
//
//}