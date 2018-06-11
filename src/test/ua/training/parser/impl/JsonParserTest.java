package ua.training.parser.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.training.entity.Currency;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class JsonParserTest {
    public static final String PATH = "src\\resources\\file.json";
    private JsonParser parser;
    @Before
    public void setUp() throws Exception {
        parser = new JsonParser(new File(PATH));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getData() {
        List<Currency> currencies = parser.getData();
        assertNotNull(currencies);
    }

    @Test
    public void saveData() {
    }
}