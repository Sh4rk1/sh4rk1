import java.io.*;
import java.util.ArrayList;

/**
 * Created by Maciek on 2015-11-20.
 */
public class RSSList {
    public ArrayList<String> RSSList = new ArrayList<String>();

    public void SaveList() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("list.bin"));
        outputStream.writeObject(RSSList);
        outputStream.close();

    }

    public void readList() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("list.bin"));
            RSSList = (ArrayList<String>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (FileNotFoundException e) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("list.bin"));
            outputStream.writeObject(RSSList);
            outputStream.close();
            readList();

        }
    }
}