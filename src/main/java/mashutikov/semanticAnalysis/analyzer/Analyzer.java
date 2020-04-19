package mashutikov.semanticAnalysis.analyzer;

import mashutikov.semanticAnalysis.model.AnalyzedNews;
import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.model.Terms;
import mashutikov.semanticAnalysis.service.PorterStemmer;
import mashutikov.semanticAnalysis.service.TermsPreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Analyzer {

    private List<AnalyzedNews> analyzedNews = new ArrayList<>();

    private Terms commonSetTerms  = new Terms();

    public List<AnalyzedNews> executeAnalysis(List<FeedMessage> news) {
        for (FeedMessage item: news) {
            this.analyzedNews.add(new AnalyzedNews(item));
        }
        for (AnalyzedNews item: analyzedNews) {
            for (String word : item.getTerms().keySet()) {
                commonSetTerms.addTerm(word);
            }
        }
        System.out.println(commonSetTerms);
        return analyzedNews;
    }

}
