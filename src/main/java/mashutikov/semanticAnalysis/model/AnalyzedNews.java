package mashutikov.semanticAnalysis.model;

import lombok.Data;
import mashutikov.semanticAnalysis.service.TermsPreparator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class AnalyzedNews {
    private String title;
    private Terms terms = new Terms();


    private TermsPreparator termsPreparator = new TermsPreparator();

    public AnalyzedNews (FeedMessage feedMessage) {
        title = feedMessage.getTitle();
        String words;
        if( feedMessage.getDescription() != null) {
            words = feedMessage.getDescription();
        } else {
            words = feedMessage.getTitle();
        }
        for (String word : words.split(" ")) {
            termsPreparator.addTerm(word);
        }
        terms = termsPreparator.getTerms();
    }
    public Map<String, Integer> getTerms(){
        return terms.getTerms();
    }
    public void setTerms(Map<String, Integer> terms){
         this.terms.setTerms(terms);
    }
    public String toString() {
        return "AnalyzedNews(title=" + this.getTitle() + ", terms=" + this.getTerms() + ")"+ "\n";
    }
}
