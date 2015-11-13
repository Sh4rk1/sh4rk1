import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Maciek on 2015-11-12.
 */
public class RSSFeedWriter {
    private String outputFile;
    private Feed rssfeed;

    public RSSFeedWriter(Feed rssfeed, String outputFile){
        this.rssfeed=rssfeed;
        this.outputFile=outputFile;
    }

    public void write() throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("");

        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);
        eventWriter.add(end);

        StartElement rssStart = eventFactory.createStartElement("", "", "rss");
        eventWriter.add(rssStart);
        eventWriter.add(eventFactory.createAttribute("version", "2.0"));
        eventWriter.add(end);

        eventWriter.add(eventFactory.createStartElement("", "", "channel"));
        eventWriter.add(end);

        createNode(eventWriter, "title", rssfeed.getTitle());
        createNode(eventWriter, "link", rssfeed.getLink());
        createNode(eventWriter, "copyright", rssfeed.getCopyright());
        createNode(eventWriter, "description", rssfeed.getDescription());
        createNode(eventWriter, "pub_Date", rssfeed.getPubDate());

        for (FeedMessage entry : rssfeed.getMessages()) {
            eventWriter.add(eventFactory.createStartElement("", "", "item"));
            eventWriter.add(end);
            createNode(eventWriter, "title", entry.getTitle());
            createNode(eventWriter, "description", entry.getDescription());
            createNode(eventWriter, "link", entry.getLink());
            createNode(eventWriter, "author", entry.getAuthor());
            createNode(eventWriter, "guid", entry.getGuid());
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndElement("", "", "item"));
            eventWriter.add(end);

        }

        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndElement("", "", "channel"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndElement("", "", "rss"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        XMLEventFactory eventFactory =  XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        StartElement startElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(startElement);
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        EndElement endElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(endElement);
        eventWriter.add(end);
    }

}

