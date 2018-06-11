package ua.training.parser.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.training.entity.Currency;
import ua.training.entity.Person;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class XmlParserTest {
    private static final String PATH = "src\\resources\\file.xml";
    private XmlParser parser;
    @Before
    public void setUp() throws Exception {
        parser = new XmlParser(new File(PATH));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getData() {
        List<Person> people = parser.getData();
        assertNotNull(people);
        
    }

    @Test
    public void saveData() {
    }
}