package ua.training.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonFileManager {
    private String path;

    public JsonFileManager(String path) {
        this.path = path;
    }

    public String loadFileAsJsonString() {
        try (BufferedReader reader =  new BufferedReader(new FileReader(path))) {
            StringBuilder buffer = new StringBuilder();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                buffer.append(currentLine);
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Currency[] getData() {
        String json = loadFileAsJsonString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Currency[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveData(Currency[] currencies) {
        try (FileWriter writer = new FileWriter(path)) {
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(currencies));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
