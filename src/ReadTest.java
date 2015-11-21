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
    private static String url;

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
                System.out.println(" 5 = Wydrukuj historie");
                System.out.println("5 = zakoncz");

                choice = scanner.nextInt();
                switch (choice) {
                        case 1:
                                rssList.readCurrentlyList();
                                printRSS();
                                MENU();
                                break;
                        case 2:
                               rssList.readCurrentlyList();
                                printList();
                                MENU();
                                break;
                        case 3:
                                rssList.readCurrentlyList();
                                rssList.readHistory();
                                feedURL();
                                checkCurrentyList();
                                addRSS();
                                rssList.saveCurrentlyList();
                                MENU();
                                break;
                        case 4:
                                rssList.readCurrentlyList();
                                System.out.println("Ktory link chcesz usunac?");
                                printList();
                                deleteIndex();
                                rssList.saveCurrentlyList();
                                MENU();
                            break;
                    case 5:
                        rssList.readHistory();
                        readHistory();
                        MENU();
                        break;
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
        public static void deleteIndex(){
        int index = scanner.nextInt();
        rssList.RSSList.remove(index-1);
    }
        public static void feedURL(){
                scanner.nextLine();
                url = scanner.nextLine();
        }
        public static void addToHistory(){
            boolean thereAlreadyIs = false;
            for(int i = 0; i<rssList.RSSHistory.size(); i++){
                if(rssList.RSSHistory.get(i).equals(url)){
                    thereAlreadyIs = true;
                }
            }
            if(thereAlreadyIs==false){
            rssList.RSSHistory.add(url);
            }
        }
        public static void checkCurrentyList() throws IOException, ClassNotFoundException {
        boolean thereAlreadyIs = false;
        rssList.readCurrentlyList();
        for (int i = 0; i < rssList.RSSList.size(); i++) {
            if (url.equals(rssList.RSSList.get(i))) {
                thereAlreadyIs = true;
            }
        }
        if (thereAlreadyIs == true) {
            System.out.println("Juz jest dodany RSS o takim adresie!");
        }
        else{
            addRSS();
        }
    }
        public static void addRSS(){
        rssList.RSSList.add(url);
        addToHistory();
    }
        public static void readHistory() throws IOException, ClassNotFoundException {
        rssList.readHistory();
        for(int i =0; i<rssList.RSSHistory.size(); i++){
            System.out.println("1. "+rssList.RSSHistory.get(i));
        }
    }
}



