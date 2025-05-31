package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object> example() {
        List<Object> list = new ArrayList<>();

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"snow29622962@gmail.com", "Ssnow12345$"});
        list.add(new Object[]{"snow28662866@gmail.com", "Ssnow12345$"});
        list.add(new Object[]{"snow13011301@gmail.com", "Ssnow12345$"});
        list.add(new Object[]{"test12@gmail.com", "vilevinQa!1234"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginModels() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setEmail("snow29622962@gmail.com").setPassword("Ssnow12345$")});
        list.add(new Object[]{new User().setEmail("snow28662866@gmail.com").setPassword("Ssnow12345$")});
        list.add(new Object[]{new User().setEmail("snow13011301@gmail.com").setPassword("Ssnow12345$")});
        list.add(new Object[]{new User().setEmail("test12@gmail.com").setPassword("vilevinQa!1234")});

        return list.iterator();
    }

}
