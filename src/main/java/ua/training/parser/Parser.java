package ua.training.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Parser<T> {
    protected File file;

    public Parser(File file) {
        this.file = file;
    }

    public abstract List<T> getData() throws IOException;

    public void saveData(List<T> t) {
        saveData(t, file);
    }

    public abstract void saveData(List<T> t, File file);
}
