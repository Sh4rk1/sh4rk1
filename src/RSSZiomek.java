import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Maciek on 2015-11-15.
 */
public class RSSZiomek {
    private final String AUTHOR = "autor";
    private final String TITLE = "tytul";
    private final String DESCRIBTION = "opis";
    private final String PUBDATE = "dataWydania";
    private final String CHANNEL = "kanal";
    private final String GUID = "identyfikator";
    private final String COPYRIGHT = "prawa";
    private final String LANGUAGE = "jezyk";
    private final String ITEM = "przedmiot";
    private final String LINK = "link";
    private final URL url;
    private InputStream inputStream = read();
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private XMLEventReader eventReader;
    private String description = "";
    private String title = "";
    private String link = "";
    private String language = "";
    private String copyright = "";
    private String author = "";
    private String pubDate = "";
    private String guid = "";

    public RSSZiomek(String enterURL){
        try{
            url = new URL(enterURL);
        }catch(MalformedURLException e){
            throw new RuntimeException();
        }
        try{
        eventReader = inputFactory.createXMLEventReader(inputStream);
    }catch (XMLStreamException e){
        throw new RuntimeException(e);
        }
    }

    public Feed readFeed(){
        Feed feed = null;
        boolean pustyNaglowek = true;
        try{
            while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                String localpart = event.asStartElement().getName().getLocalPart();
                switch (localpart) {
                    case ITEM:
                        if (pustyNaglowek) {
                            pustyNaglowek = false;
                            feed = new Feed(title, description, language, author, copyright, pubDate);
                        }
                        event = eventReader.nextEvent();
                    case TITLE:
                        title = getCharacter(event, eventReader);
                        break;
                    case DESCRIBTION:
                        description = getCharacter(event, eventReader);
                        break;
                    case LANGUAGE:
                        language = getCharacter(event, eventReader);
                        break;
                    case AUTHOR:
                        author = getCharacter(event, eventReader);
                        break;
                    case COPYRIGHT:
                        copyright = getCharacter(event, eventReader);
                        break;
                    case PUBDATE:
                        pubDate = getCharacter(event, eventReader);
                        break;
                    case GUID:
                        guid = getCharacter(event, eventReader);
                }
            }
            else if (event.isEndElement()) {
                if(event.asEndElement().getName().getLocalPart()==ITEM){
                    FeedMessage uzupelnnij = new FeedMessage();
                    uzupelnnij.setTitle(title);
                    uzupelnnij.setLink(link);
                    uzupelnnij.setDescription(description);
                    uzupelnnij.setGuid(guid);
                    uzupelnnij.setAuthor(author);
                    feed.entries.add(uzupelnnij);

                }

            }
        }
        }catch (Exception e){
            throw new RuntimeException();
        }
return feed;
    }

    public  InputStream read(){
        try{
           return url.openStream();
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public String getCharacter(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String results;
        event = eventReader.nextEvent();
        results =  event.asCharacters().getData();
        return results;
    }

}
