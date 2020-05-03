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
import java.util.stream.Collectors;

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
        commonSetTerms.setTerms(commonSetTerms.getTerms().entrySet()
                .stream()
                .filter(term -> term.getValue() > 1)
                .collect(Collectors.toMap(term -> term.getKey(), term -> term.getValue())));
        for (AnalyzedNews item: analyzedNews) {
            item.setTerms(item.getTerms().entrySet()
                    .stream()
                    .filter(term -> commonSetTerms.getTerms().get(term.getKey()) != null)
                    .collect(Collectors.toMap(term -> term.getKey(), term -> term.getValue())));
        }
        return analyzedNews;
    }

}
