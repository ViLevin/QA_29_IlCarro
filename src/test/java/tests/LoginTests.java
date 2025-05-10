package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
    //if 'Sign Out' present ---> logout
        if(app.getHelperUser().isLogged()){
        app.getHelperUser().logout();}

    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12@gmail.com", "vilevinQa!1234");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
         app.getHelperUser().clickOkButton();


    }

    @Test
    public void loginUnsuccessEmail() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12gmail.com", "vilevinQa!1234");
        Assert.assertTrue(app.getHelperUser().isExistError(), "It's not look like email");
    }

    @Test
    public void loginUnsuccessPassword() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("test12@gmail.com", "vilevinQa1234");
        app.getHelperUser().submitLogin();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        app.getHelperUser().clickOkButton();


    }

}
