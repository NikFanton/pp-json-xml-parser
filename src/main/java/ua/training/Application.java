package ua.training.xml;

import ua.training.parser.XmlParser;

public class Application {
    public static void main(String[] args) {
        Person[] notebook = { new Person(1L, "John", "address111", 11000, "Master"),
                            new Person(2L, "Peter", "address123", 8000, "Bachelor"),
                            new Person(3L, "Nikita", "address256", 12000, "Bachelor") };

        XmlParser xmlFileManager = new XmlParser("src\\main\\resources\\file.xml");
//        xmlFileManager.saveData(notebook);
        xmlFileManager.getData().forEach(System.out::println);
    }
}
