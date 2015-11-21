import java.io.*;
import java.util.ArrayList;

/**
 * Created by Maciek on 2015-11-20.
 */
public class RSSList {
    public ArrayList<String> RSSList = new ArrayList<String>();
    public ArrayList<String> RSSHistory = new ArrayList<>();
    private String file;

    public void ReadFile() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            RSSList = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (FileNotFoundException e) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(RSSList);
            outputStream.close();
            ReadFile();
        }
    }

    public void SaveFile() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(RSSList);
        outputStream.close();
    }


    public void saveCurrentlyList() throws IOException {
        file = "list.bin";
        SaveFile();

    }

    public void readCurrentlyList() throws IOException, ClassNotFoundException {
        file = "list.bin";
        ReadFile();
    }

    public void readHistory() throws IOException, ClassNotFoundException {
        file = "history.bin";
        ReadFile();
    }
    public void saveHistory() throws IOException {
        file = "history.bin";
        SaveFile();
    }
}

