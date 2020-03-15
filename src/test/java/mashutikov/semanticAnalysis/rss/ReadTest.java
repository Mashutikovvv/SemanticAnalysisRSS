package mashutikov.semanticAnalysis.rss;

import mashutikov.semanticAnalysis.model.Feed;
import mashutikov.semanticAnalysis.model.FeedMessage;
import org.junit.jupiter.api.Test;

public class ReadTest {
    @Test
    void test() {
        RSSFeedParser parser = new RSSFeedParser(
                "https://lenta.ru/rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }

    }
}
