import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciek on 2015-11-11.
 */
public class Feed {

    final String title;
    final String description;
    final String link;
    final String language;
    final String copyright;
    final String pubDate;

    final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed(String title, String description, String link, String language, String copyright, String pubDate){
        this.title=title;
        this.description=description;
        this.link=link;
        this.language=language;
        this.copyright=copyright;
        this.pubDate=pubDate;
    }
    public List<FeedMessage> getMessages(){
        return entries;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getLink(){
        return link;
    }
    public String getLanguage(){
        return language;
    }
    public String getCopyright(){
        return copyright;
    }
    public String getPubDate(){
        return pubDate;
    }
    public String toString(){
        return "Freed: [Title: " +title+", Description: "+description+", Copyright: "+copyright+", Language: "+language+", PubDate: "+pubDate;
    }
}

