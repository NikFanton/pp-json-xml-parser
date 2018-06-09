package ua.training.json;

public class Application {
    public static void main(String[] args) {
        JsonFileManager manager = new JsonFileManager("src\\main\\resources\\file.json");
        Currency[] data = manager.getData();
        for (Currency currency : data) {
            System.out.println(currency);
        }
        manager.saveData(data);
    }
}
