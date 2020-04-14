package mashutikov.semanticAnalysis.analyzer;

import mashutikov.semanticAnalysis.model.AnalyzedNews;
import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.service.PorterStemmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Analyzer {

    private List<AnalyzedNews> news;


    private PorterStemmer stemmer = new PorterStemmer();

    public Analyzer(List<FeedMessage> news) {
        this.news = new ArrayList<>();
        for (FeedMessage item: news) {
            this.news.add(new AnalyzedNews(item));
        }

    }

    public List<AnalyzedNews> executeAnalysis() {
        for (AnalyzedNews item: news) {
            for (int i = 0; i < item.getDescription().size(); i++) {
                stemmer.stem(item.getDescription().get(i));
                item.getDescription().set(i, stemmer.stem(item.getDescription().get(i)));
            }
        }
        return news;
    }

}
