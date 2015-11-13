/**
 * Created by Maciek on 2015-11-11.
 */
public class FeedMessage {
    String title;
    String description;
    String link;
    String author;
    String guid;

    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    public void setLink(String link){
        this.link=link;
    }
    public String getLink(){
        return link;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public String getAuthor(){
        return author;
    }
    public void setGuid(String guid){
        this.guid=guid;
    }
    public String getGuid(){
        return guid;
    }
    public String toString(){
        return "FeedMessage: [title= "+title+", Description= "+description+", Link= "+link+", Author: "+author+", Guid= "+guid;
    }


}
