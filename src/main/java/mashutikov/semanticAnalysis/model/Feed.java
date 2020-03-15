package mashutikov.semanticAnalysis.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Feed {
    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;
    final List<FeedMessage> messages = new ArrayList<>();
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }
}
