package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillingLoginForm("", "");
        app.getHelperUser().submitLogin();
//        Assert if element with text "Logged in success" is present
        app.getHelperUser().clickOkButton();

    }


}
