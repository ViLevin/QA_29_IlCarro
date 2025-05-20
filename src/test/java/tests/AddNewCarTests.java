package tests;

import models.Car;
import models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddNewCarTests extends TestBase {


    @BeforeClass
//    login
    public void preCondition() {

//            if (!app.getHelperUser().isLogged()) {
//                app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
//            }
//        }
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
//            app.getHelperCar().submit();
            System.out.println("Before sleep");
            app.getHelperUser().pause(3000);
            System.out.println("After sleep");
            app.getHelperCar().submit();
//            WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
//            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='Ok']")));
//            if (okButton != null) {
//                okButton.click();
//            }
            app.getHelperUser().clickOkButton();
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
                .carRegNumber("103-900-55" + i)
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


    @Test(enabled = false)
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


    @Test(enabled = false)
    public void addNewCarUnsuccess() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufactura("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900" + i)
                .price(50.)
                .about("Very nice car")
                .build();

//        app.getHelperCar().openCarForm();
//        app.getHelperCar().fillCarForm();
//        app.getHelperCar().submitCarForm();

    }

    @Test(enabled = false)
    public void addNewCarUnsuccessLocation() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("")
                .manufactura("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900" + i)
                .price(50.)
                .about("Very nice car")
                .build();

//        app.getHelperCar().openCarForm();
//        app.getHelperCar().fillCarForm();
//        app.getHelperCar().submitCarForm();
        Assert.assertEquals("Wrong address", app.getHelperUser().getErrorText());

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

//        app.getHelperCar().openCarForm();
//        app.getHelperCar().fillCarForm();
//        app.getHelperCar().submitCarForm();


//        Assert.assertEquals("Make is required", app.getHelperCar().
    }

    @AfterMethod
    public void PostCondition() {
        app.getHelperCar().returnToHomePage();

    }
}
