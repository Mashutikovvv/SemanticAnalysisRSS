package mashutikov.semanticAnalysis.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Feed {
    private int totalResults;
    private List<FeedMessage> articles = new ArrayList<>();
}
