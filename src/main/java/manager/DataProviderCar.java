package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DataProviderCar {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPrice() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/car.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{Car.builder()
                    .location(all[0])
                    .manufactura(all[1])
                    .model(all[2])
                    .year(all[3])
                    .fuel(all[4])
                    .seats(Integer.parseInt(all[5]))
                    .carClass(all[6])
                    .carRegNumber(all[7])
                    .price(Double.parseDouble(all[8]))
                    .about(all[9])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }

}
