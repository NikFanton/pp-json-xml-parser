package ua.training.parser.impl;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.training.entity.Currency;
import ua.training.entity.Person;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class XmlParserTest {
    private static final String PATH = "src/resources/file.xml";
    private List<Person> notebook;
    private XmlParser parser;

    @Before
    public void setUp() {
        parser = new XmlParser(new File(PATH));
        notebook = Arrays.asList(new Person(1L, "John", "address111", 11000),
                new Person(2L, "Peter", "address123", 8000),
                new Person(3L, "Nikita", "address256", 12000));
    }

    @Test
    public void getData() {
        List<Person> people = parser.getData();
        assertNotNull(people);
    }

    @Test
    public void saveData() {
        parser.saveData(notebook);
        List<Person> actual = parser.getData();
        Assert.assertNotNull(actual);
        Assert.assertEquals(notebook.size(), actual.size());
        for (Person person : notebook) {
            Assert.assertTrue(actual.contains(person));
        }
    }

    @Test(expected = NullPointerException.class)
    public void whenNullFileThrowException() {
        new XmlParser(null);
    }

    @Test(expected = RuntimeException.class)
    public void saveDataToNullFile() {
        parser.saveData(notebook, null);
    }
}