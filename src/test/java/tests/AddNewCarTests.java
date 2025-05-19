package tests;

import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {


    @BeforeClass
//    login
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234"));
        }
    }

    @Test
    public void addNewCarSuccessRequired() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("TelAviv, Israel")
                .manufactura("Mazda")
                .model("M3")
                .year("2022")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("123-900-55" + i)
                .price(50.)
//                .about("Very nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

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
        app.getHelperCar().submit();

    }

    @Test
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

    @Test
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

    @Test
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

}
