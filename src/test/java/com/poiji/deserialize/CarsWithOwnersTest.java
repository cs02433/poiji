package com.poiji.deserialize;

import com.poiji.bind.Poiji;
import com.poiji.deserialize.model.CarsWithOwners;
import com.poiji.option.PoijiOptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class CarsWithOwnersTest {


    private String path;

    public CarsWithOwnersTest(String path) {
        this.path = path;
    }

    @Parameterized.Parameters(name = "{index}: ({0})={1}")
    public static Iterable<Object[]> queries() {
        return Arrays.asList(new Object[][]{
                {"src/test/resources/carsWithOwners.xlsx"},
        });
    }


    @Test
    public void shouldParseColumnUsingCustomCellDeserializer() {
        List<CarsWithOwners> cars = createObjectUnderTest();

        Assert.assertEquals(cars.size(), 2);
        List<String> owners1 = cars.get(0).getOwners();
        Assert.assertEquals(owners1.size(), 3);

        List<String> owners2 = cars.get(1).getOwners();
        Assert.assertEquals(owners2.size(), 2);

        Assert.assertEquals("MukeshAnilVedant", owners1.stream().reduce((x, y) -> x + y).get());
        Assert.assertEquals("GuddoAkku", owners2.stream().reduce((x, y) -> x + y).get());


    }

    @Test
    public void shouldSetAdultTypeCorrectlyUsingCustomParser() {
        List<CarsWithOwners> cars = createObjectUnderTest();

        String isAdult1 = cars.get(0).getIsAdult();

        String isAdult2 = cars.get(1).getIsAdult();

       Assert.assertEquals(isAdult1, "Adult");
        Assert.assertEquals(isAdult2, "TEEN");

    }

    private List<CarsWithOwners> createObjectUnderTest() {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("M/d/")
                .appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 2, LocalDate.now().minusYears(80)).toFormatter();

        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(0).dateTimeFormatter(formatter).build();

        return Poiji.fromExcel(new File(path), CarsWithOwners.class, options);
    }
}
