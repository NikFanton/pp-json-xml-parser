package ua.training.parser.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.training.entity.Currency;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JsonParserTest {
    private static final String PATH = "src/resources/file.json";
    private List<Currency> currencies;
    private JsonParser parser;

    @Before
    public void setUp() {
        parser = new JsonParser(new File(PATH));
        currencies = Arrays.asList( new Currency(840, "Долар США", 26.136416, "USD", "11.06.2018"),
                new Currency(978, "Євро", 30.720743000000006, "EUR", "11.06.2018"),
                new Currency(643, "Російський рубль", 0.41706000000000004, "RUB", "11.06.2018"));
    }

    @Test
    public void getData() {
        List<Currency> currencies = parser.getData();
        assertNotNull(currencies);
    }

    @Test
    public void saveData() {
        parser.saveData(currencies);
        List<Currency> actual = parser.getData();
        Assert.assertNotNull(currencies);
        Assert.assertEquals(currencies.size(), actual.size());
        for (Currency c : currencies) {
            Assert.assertTrue(actual.contains(c));
        }
    }

    @Test(expected = NullPointerException.class)
    public void whenNullFileThrowException() {
        new JsonParser(null);
    }

    @Test(expected = RuntimeException.class)
    public void saveDataToNullFile() {
        parser.saveData(currencies, null);
    }
}