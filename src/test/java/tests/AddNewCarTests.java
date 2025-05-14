package tests;

import models.Car;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {


    @BeforeClass
//    login
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
        }
    }

    @Test
    public void addNewCarSuccess() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Car car = Car.builder()
                .location("TelAviv, Israel")
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
}
