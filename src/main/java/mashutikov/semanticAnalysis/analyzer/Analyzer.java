package mashutikov.semanticAnalysis.analyzer;

import mashutikov.semanticAnalysis.model.AnalyzedNews;
import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.service.PorterStemmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            for (String word: item.getDescription()) {
                System.out.println(stemmer.stem(word));
            }
        }
        return news;
    }

}
