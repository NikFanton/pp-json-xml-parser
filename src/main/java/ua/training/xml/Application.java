package ua.training.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Application {
    public static void main(String[] args) {
        Person[] notebook = { new Person(1L, "John", "address111", 11000, "Master"),
                            new Person(2L, "Peter", "address123", 8000, "Bachelor"),
                            new Person(3L, "Nikita", "address256", 12000, "Bachelor") };

        XmlFileManager xmlFileManager = new XmlFileManager("src\\main\\resources\\file.xml");
//        xmlFileManager.saveData(notebook);
        xmlFileManager.getData().forEach(System.out::println);
    }
}
