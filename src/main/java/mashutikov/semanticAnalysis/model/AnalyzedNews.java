package mashutikov.semanticAnalysis.model;

import lombok.Data;

@Data
public class AnalyzedNews {
    private String title;
    private String[] description;
    public AnalyzedNews (FeedMessage feedMessage) {
        this.title = feedMessage.getTitle();
        this.description = feedMessage.getDescription().split(" ");
    }
}
