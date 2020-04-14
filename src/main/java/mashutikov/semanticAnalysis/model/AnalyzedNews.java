package mashutikov.semanticAnalysis.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class AnalyzedNews {
    private String title;
    private List<String> description = new ArrayList<>();
    public AnalyzedNews (FeedMessage feedMessage) {
        this.title = feedMessage.getTitle();
        this.description = Arrays.asList(feedMessage.getDescription().split(" "));
    }
}
