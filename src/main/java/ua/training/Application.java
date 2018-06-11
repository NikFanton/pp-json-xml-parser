package ua.training;

import ua.training.entity.Person;
import ua.training.parser.impl.XmlParser;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Person> notebook = Arrays.asList(new Person(1L, "John", "address111", 11000, "Master"),
                new Person(2L, "Peter", "address123", 8000, "Bachelor"),
                new Person(3L, "Nikita", "address256", 12000, "Bachelor"));

        XmlParser xmlFileManager = new XmlParser(new File("src\\main\\resources\\file.xml"));
        xmlFileManager.saveData(notebook);
        xmlFileManager.getData().forEach(System.out::println);

//        JsonParser manager = new JsonParser(new File("src\\main\\resources\\file.json"));
//        List<Currency> data = manager.getData();
//        for (Currency currency : data) {
//            System.out.println(currency);
//        }
//        manager.saveData(data);
    }
}
