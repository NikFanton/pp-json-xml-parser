package ua.training;

public abstract class Parser<T> {
    protected String path;

    public Parser(String path) {
        this.path = path;
    }

    public abstract T[] getData();

    public abstract void saveData(T[] t);
}
