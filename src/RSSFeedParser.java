import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Maciek on 2015-11-15.
 */
public class RSSFeedParser {

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LANGUAGE = "language";
    private static final String COPYRIGHT = "copyright";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";
    private static final String ITEM = "item";
    private static final String PUB_DATE = "pubDate";
    private static final String GUID = "guid";
    private static String description = "";
    private static String title = "";
    private static String link = "";
    private static String language = "";
    private static String copyright = "";
    private static String author = "";
    private static String pubDate = "";
    private static String guid = "";
    private Feed feed = null;

    public InputStream in;
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private XMLEventReader eventReader;


    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            url = new URL(feedUrl);
            in = read();
            eventReader = inputFactory.createXMLEventReader(in);
            read().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Feed readFeed() {
        try {
            boolean isFeedHeader = true;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language, copyright, pubDate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case PUB_DATE:
                            pubDate = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            eventReader.close();
                            break;
                    }
                    eventReader.close();
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        setAllElements();
                    }
                }
            }

        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    public Feed setAllElements() throws XMLStreamException {
        FeedMessage message = new FeedMessage();
        message.setAuthor(author);
        message.setDescription(description);
        message.setGuid(guid);
        message.setLink(link);
        message.setTitle(title);
        feed.getMessages().add(message);
        return feed;
    }


    private InputStream read() throws XMLStreamException {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
        String results = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            results = event.asCharacters().getData();
        }
        return results;
    }
}