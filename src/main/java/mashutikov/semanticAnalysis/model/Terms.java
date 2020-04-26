package mashutikov.semanticAnalysis.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Terms {
    private Map<String, Integer> terms = new HashMap<>();

    public void addTerm(String term) {
        if(terms.get(term) == null) {
            terms.put(term, 1);
        } else {
            int newValue = terms.get(term);
            newValue++;
            terms.put(term, newValue);
        }
    }

    public int size(){
        return terms.size();
    }
}
