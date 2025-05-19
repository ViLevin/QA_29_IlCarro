package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        //if 'Sign Out' present ---> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }

    }

    @Test
    public void registrationSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
//        ==========================================
        int z = (int) ((System.currentTimeMillis() - 1000) % 3600);
        System.out.println(System.currentTimeMillis());
        System.out.println(z);


        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + z + "@gmail.com")
                .setPassword("Ssnow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);

        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");

    }

    @Test
    public void registrationEmptyName() {
        User user = new User()
                .setFirstName("")
                .setLastName("Slow")
                .setEmail("slowslow@gmail.com")
                .setPassword("Sslow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Name is required");
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
    }

    @Test
    public void registrationEmptyLastname() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("")
                .setEmail("slowslow@gmail.com")
                .setPassword("Sslow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Last name is required");
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
    }


    @Test //(enabled = false)
    public void registrationEmptyEmail() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("")
                .setPassword("Sslow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        app.getHelperUser().isYallaBtnNotActive();
    }

    @Test//(enabled = false)
    public void registrationEmptyPassword() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("slowslow@gmail.com")
                .setPassword("");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        app.getHelperUser().isYallaBtnNotActive();
    }

    @Test//(enabled = false)
    public void registrationWrongEmail() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("slowslowgmail.com")
                .setPassword("Sslow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().getErrorText().contains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
    }

    @Test//(enabled = false)
    public void registrationWrongPassword() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("slowslow@gmail.com")
                .setPassword("slow1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());

    }


    @Test//(enabled = false)
    public void registrationExistUser() {
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("test12@gmail.com")
                .setPassword("vilevinQa!1234");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"User already exists\"");

    }


    @Test//(enabled = false)
    public void registrationPolicyButtonNotChecked() {
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("sllow" + i + "@gmail.com")
                .setPassword("Sslow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().pause(500);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());


    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
