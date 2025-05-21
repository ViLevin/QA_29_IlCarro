package tests;

import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddNewCarTests extends TestBase {

    @BeforeClass
//    login app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
        }
    }


    @Test
    public void addNewCarSuccessAll() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("103-900-22" + i)
                .price(50.)
                .about("Very nice car")
                .build();


        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().attachPhoto("D:\\Vi\\QA_Auto\\QA_29_IlCarro\\DeLorean.jpg");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperUser().getMessage(), car.getManufactura() + " " + car.getModel() + " " + "added successful");

    }


    @Test
    public void addNewCarSuccessRequired() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("TelAviv, Israel")
                .manufactura("Opel")
                .model("Tigra")
                .year("2000")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("555-900-55" + i)
                .price(50.)
                .build();


        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperUser().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperUser().getMessage(), car.getManufactura() + " " + car.getModel() + " " + "added successful");

    }



    @Test
    public void addNewCarUnsuccessLocation() {
                Car car = Car.builder()
                .location("")
                .manufactura("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-77-111")
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();

        WebDriver wd = app.getDriver();
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));

        WebElement pickUpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickUpPlace")));
//        WebElement pickUpInput = wd.findElement(By.id("pickUpPlace"));
        pickUpInput.sendKeys("Tel Aviv, Israel");

        try {
            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.pac-item")));
            suggestion.click();
        } catch (TimeoutException e) {
            System.out.println("⚠ div.pac-item not shown — autocomplete failed or blocked");
        }

        pickUpInput.clear();

        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();
        Assert.assertEquals("Wrong address", app.getHelperUser().getErrorText());
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());

    }

    @Test(enabled = false)
    public void addNewCarUnsuccessManufacture() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900" + i)
                .price(50.)
                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();


//        Assert.assertEquals("Make is required", app.getHelperCar().
    }

    @Test
//    public void addNewCarUnsuccess() {
//        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
//
//        Car car = Car.builder()
//                .location("Tel Aviv, Israel")
//                .manufactura("Mazda")
//                .model("M3")
//                .year("2022")
//                .fuel("Petrol")
//                .seats(4)
//                .carClass("C")
//                .carRegNumber("678-900" + i)
//                .price(50.)
//                .about("Very nice car")
//                .build();
//
//        app.getHelperCar().openCarForm();
//        app.getHelperCar().fillCarForm(car);
//        app.getHelperCar().submit();
//
//        Assert.assertTrue(app.getHelperUser().getMessage().contains("assed successful"));
//        Assert.assertEquals(app.getHelperUser().getMessage(), car.getManufactura() + " " + car.getModel() + " " + "added successful");
//
//    }


    @AfterMethod(enabled = false)
    public void PostCondition() {
        app.getHelperCar().returnToHomePage();

    }
}
