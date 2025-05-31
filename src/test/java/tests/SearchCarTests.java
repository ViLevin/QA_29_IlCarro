package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase {

    @Test(enabled = false)
    public void searchCurrentMonthSuccess() {
        app.getHelperCar().searchCurrentMonth("Tel Aviv", "5/31/2025", "5/31/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess() {
        app.getHelperCar().searchCurrentYear("Tel Aviv", "7/3/2025", "11/15/2025");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess() {
        app.getHelperCar().searchAnyPeriod("Tel Aviv", "10/09/2025", "3/8/2026");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

}
