package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        //if 'Sign Out' present ---> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }

    }

    @Test
    public void loginSuccessObject() {
        User user = new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");


    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12@gmail.com", "vilevinQa!1234");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");


    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12@gmail.com", "vilevinQa!1234");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginWrongEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12gmail.com", "vilevinQa!1234");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It's not look like email");
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
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
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
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
//        Assert.assertEquals(app.getHelperUser().getMessage(), "Password is required");
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }

    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }

}
