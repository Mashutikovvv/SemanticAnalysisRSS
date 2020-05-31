package mashutikov.semanticAnalysis.service;

import mashutikov.semanticAnalysis.dto.ResourcesDto;
import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.service.apiClient.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NewsFetcher {

    private static final String[] SOURCES = {"google-news-ru", "lenta", "rbc", "rt"};
    private static final String RUS_LANG = "ru";
    @Autowired
    private ApiClient apiClient;

    public List<FeedMessage> fetchNews(ResourcesDto dto) {
        List<FeedMessage>  newsList;
        newsList = new ArrayList<>();
        newsList.addAll(apiClient.getNews(SOURCES, RUS_LANG, dto.getQ(), dto.getFrom(), dto.getTo()).getArticles());
        newsList = newsList.stream().filter(distinctByKey(FeedMessage::getUrl)).collect(Collectors.toList());
        return newsList;
    }
    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
