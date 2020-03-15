package mashutikov.semanticAnalysis.service;

import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.rss.RSSFeedParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsFetcher {
    public List<FeedMessage> fetchNews(List<String> urls) {
        List<FeedMessage>  newsList = new ArrayList<>();
        for (String url: urls) {
            RSSFeedParser parser = new RSSFeedParser(url);
            newsList.addAll(parser.readFeed().getMessages());
        }
        return newsList;
    }
}
