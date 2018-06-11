package ua.training.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.training.entity.Currency;

import java.io.*;
import java.util.List;

public class JsonParser extends Parser<Currency> {
    public JsonParser(File file) {
        super(file);
    }

    public List<Currency> getData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, mapper.getTypeFactory()
                    .constructCollectionType(List.class, Currency.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveData(List<Currency> currencies) {
        try (FileWriter writer = new FileWriter(file)) {
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(currencies));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
