import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Maciek on 2015-11-12.
 */
public class ReadTest {
    private static Scanner scanner = new Scanner(System.in);
     private static RSSList rssList = new RSSList();

        public static void main(String[] args) throws XMLStreamException, IOException, ClassNotFoundException {
        MENU();
        }

    public static void MENU() throws IOException, ClassNotFoundException {
                int choice;
                System.out.println("MENU:");
                System.out.println(" 1 = Wydrukuj RSS");
                System.out.println(" 2 = Pokaz liste dodanych RSS");
                System.out.println(" 3 = Dodaj link RSS");
                System.out.println(" 4 = Usun link RSS");
                System.out.println("5 = zakoncz");
                choice = scanner.nextInt();
                switch (choice) {
                        case 1:
                                rssList.readList();
                                printRSS();
                                MENU();
                                break;
                        case 2:
                               rssList.readList();
                                printList();
                                MENU();
                                break;
                        case 3:
                                rssList.readList();
                                addRSS();
                                rssList.SaveList();
                                MENU();
                                break;
                        case 4:
                                rssList.readList();
                                System.out.println("Ktory link chcesz usunac?");
                                printList();
                                deleteIndex();
                                rssList.SaveList();
                                MENU();
                }
            }

        public static void printRSS() {
                for (int i = 0; i < rssList.RSSList.size(); i++) {
                        String righturl = rssList.RSSList.get(i);
                        RSSFeedParser parser = new RSSFeedParser(righturl);
                        Feed feed = parser.readFeed();
                        System.out.println(feed);
                        for (FeedMessage message : feed.getMessages()) {
                                System.out.println(message);
                        }
                }
        }

        public static void printList(){
                for(int i = 0; i<rssList.RSSList.size(); i++){
                        System.out.println(i+1 +". "+rssList.RSSList.get(i));
                }
        }

        public static void addRSS(){
                scanner.nextLine();
                String url = scanner.nextLine();
                rssList.RSSList.add(url);
        }

        public static void deleteIndex(){
                int index = scanner.nextInt();
                rssList.RSSList.remove(index-1);
        }
}



