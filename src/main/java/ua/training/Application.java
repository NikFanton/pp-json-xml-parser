package ua.training;

import org.apache.commons.io.FileUtils;
import ua.training.entity.Currency;
import ua.training.entity.Person;
import ua.training.parser.impl.JsonParser;
import ua.training.parser.impl.XmlParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        useXmlParser();
        useJsonParser();
    }

    private static void useJsonParser() {
        try {
            URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
            File file = new File("src/main/resources/file.json");
            FileUtils.copyURLToFile(url, file);
            List<String> actualCurrencies = Arrays.asList("RUB", "USD", "EUR");

            JsonParser jsonParser = new JsonParser(file);
            List<Currency> currencies = jsonParser.getData();
            currencies = currencies.stream()
                    .filter(currency -> actualCurrencies.contains(currency.getCc()))
                    .collect(Collectors.toList());
            jsonParser.saveData(currencies);
            currencies.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void useXmlParser() {
        File resourceFile = new File("src/main/resources/file.xml");
        File resultFile = new File("src/main/resources/result.xml");
        List<Person> notebook = Arrays.asList(new Person(1L, "John", "address111", 11000),
                new Person(2L, "Peter", "address123", 8000),
                new Person(3L, "Nikita", "address256", 12000));

        XmlParser parser = new XmlParser(resourceFile);
        parser.saveData(notebook);
        List<Person> peopleFromNotebook = parser.getData();
        peopleFromNotebook = peopleFromNotebook.stream()
                .filter(person -> person.getCash() >= 10000)
                .collect(Collectors.toList());
        peopleFromNotebook.forEach(System.out::println);
        parser.saveData(peopleFromNotebook, resultFile);
    }
}
