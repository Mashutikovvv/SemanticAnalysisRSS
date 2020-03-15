package mashutikov.semanticAnalysis.model;

import lombok.Data;

@Data
public class FeedMessage {
    String title;
    String description;
    String link;
    String author;
    String guid;
}
