/**
 * Created by Maciek on 2015-11-12.
 */
public class ReadTest {

    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser("http://newsrss.bbc.co.uk/rss/sportonline_uk_edition/other_sports/rss.xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
            RSSFeedWriter writer = new RSSFeedWriter(feed, "articles.rss");
            try {
                writer.write();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}

