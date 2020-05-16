package mashutikov.semanticAnalysis.service;

import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.service.apiClient.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsFetcher {

    private static final String[] SOURCES = {"google-news-ru", "lenta", "rbc", "rt"};
    private static final String RUS_LANG = "ru";
    @Autowired
    private ApiClient apiClient;

    public List<FeedMessage> fetchNews() {
        List<FeedMessage>  newsList;
        newsList = new ArrayList<>();
        newsList.addAll(apiClient.getNews(SOURCES, RUS_LANG).getArticles());

        return newsList;
    }
}
