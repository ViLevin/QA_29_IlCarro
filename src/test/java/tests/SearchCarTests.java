package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class SearchCarTests extends TestBase {

    @Test
    public void searchCurrentMonthSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        app.getHelperCar().searchCurrentMonth("Tel Aviv", "6/6/2025", "6/25/2025");
        app.getHelperUser().getScreen("src/test/screenshots/currentMonth" + i + ".png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        app.getHelperCar().searchCurrentYear("Tel Aviv", "7/3/2025", "11/15/2025");
        app.getHelperUser().getScreen("src/test/screenshots/currentYear" + i + ".png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        app.getHelperCar().searchAnyPeriod("Tel Aviv", "10/9/2025", "3/8/2026");
        app.getHelperUser().getScreen("src/test/screenshots/currentAnyPeriod" + i + ".png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch() {
        app.getHelperCar().searchNotValidPeriod("Tel Aviv", "3/9/2025", "6/27/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isYallaBtnNotActive());
        Assert.assertTrue(app.getHelperUser().getErrorText().contains("You can't pick date before today"));
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperCar().navigateByLogo();
    }

}
