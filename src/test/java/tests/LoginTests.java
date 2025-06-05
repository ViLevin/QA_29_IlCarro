package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        //if 'Sign Out' present ---> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finish logout");
        }

    }

    @Test(dataProvider = "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessObject(User user) {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
//        logger.info("Start test with name'loginSuccessObject'");
//        logger.info("Start");
//        User user = new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginFormObj(user);
        logger.info("TEST DATA  - - - >  " + user.toString());
        app.getHelperUser().submit();
        app.getHelperCar().getScreen("src/test/screenshots" + i + ".png");
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        logger.info("Assert check message 'Logged in success'");


        logger.info("End");

    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(email, password);
        logger.info("TEST DATA  - - - >   email: " + email + " password: " + password);

        app.getHelperUser().submit();
        app.getHelperCar().getScreen("src/test/screenshots" + i + ".png");
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        logger.info("Assert check message 'Logged in success'");
    }


    @Test(dataProvider = "loginModels", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginFormObj(user);
        logger.info("TEST DATA  - - - >  " + user.toString());
        app.getHelperUser().submit();
        app.getHelperCar().getScreen("src/test/screenshots" + i + ".png");
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        logger.info("Assert check alert message 'Logged in success'");
    }

    @Test(dataProvider = "loginFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessDPF(User user) {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginFormObj(user);
        logger.info("TEST DATA  - - - >  " + user.toString());
        app.getHelperUser().submit();
        app.getHelperCar().getScreen("src/test/screenshots/logDFPposit" + i + ".png");
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

        logger.info("Assert check alert message 'Logged in success'");
    }


    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12gmail.com", "vilevinQa!1234");
        logger.info("Test data ---> email: 'testest12gmail.com' & password: 'vilevinQa!1234'");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        logger.info("Assert check alert message 'It'snot look like email'");
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
    }

//    @Test
//    public void loginEmptyEmail() {
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("", "vilevinQa!1234");
//        app.getHelperUser().submit();
//        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
//        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
//    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12@gmail.com", "vilevinQa1234");
        logger.info("Test data ---> email: 'test12@gmail.com' & password: 'vilevinQa1234'");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check alert message 'Login or Password incorrect'");
//        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
    }


    //    @Test
//    public void loginEmptyPassword() {
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("test12gmail.com", "");
//        app.getHelperUser().submit();
//        Assert.assertTrue(app.getHelperUser().isExistError(), "Password is required");
//        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
//    }


    @Test
    public void loginUnregisteredUser() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("0535592367vi@gmail.com", "vilevinQa!1234");
        logger.info("Test data ---> email: '0535592367vi@gmail.com' & password: 'vilevinQa!1234'");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
//        Assert.assertEquals(app.getHelperUser().getMessage(), "Password is required");
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Assert check alert message 'Login or Password incorrect'");

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }

}
