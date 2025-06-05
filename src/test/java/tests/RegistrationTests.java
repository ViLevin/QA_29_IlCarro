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
        logger.info("Start");
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
//        ==========================================
        int z = (int) ((System.currentTimeMillis() - 1000) % 3600);
        System.out.println(System.currentTimeMillis());
        System.out.println(z);


        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Snow")
                .setEmail("snow" + z + z + "@gmail.com")
                .setPassword("Ssnow12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        logger.info("TEST DATA: -->" + user.toString());

        app.getHelperUser().checkPolicyXY();
        app.getHelperCar().getScreen("src/test/screenshots/registration" + i + ".png");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
        logger.info("End");
    }

    @Test
    public void registrationEmptyName() {
        logger.info("Start");
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
        logger.info("End");
    }

    @Test
    public void registrationEmptyLastname() {
        logger.info("Start");
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
        logger.info("End");
    }


    @Test
    public void registrationEmptyEmail() {
        logger.info("Start");
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
        logger.info("End");
    }

    @Test
    public void registrationEmptyPassword() {
        logger.info("Start");
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
        logger.info("End");
    }

    @Test//(enabled = false)
    public void registrationWrongEmail() {
        logger.info("Start");
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
        logger.info("End");
    }

    @Test
    public void registrationWrongPassword() {
        logger.info("Start");
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
        logger.info("End");
    }


    @Test
    public void registrationExistUser() {
        logger.info("Start");
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
        logger.info("End");
    }


    @Test
    public void registrationPolicyButtonNotChecked() {
        logger.info("Start");
        int i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        User user = new User()
                .setFirstName("Lisa")
                .setLastName("Slow")
                .setEmail("sllow" + i + "@gmail.com")
                .setPassword("Sslow12345$");

        app.getDriver().navigate().refresh();

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().pause(500);
        app.getHelperUser().submit();

        Assert.assertTrue(app.getHelperUser().isYallaBtnNotActive());
        logger.info("End");
    }


    @AfterMethod
    public void postCondition() {
        app.getHelperUser().clickOkButton();
    }
}
