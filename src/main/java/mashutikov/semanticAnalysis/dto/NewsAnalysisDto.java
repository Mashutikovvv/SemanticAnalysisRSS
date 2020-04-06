package mashutikov.semanticAnalysis.dto;

import lombok.Data;
import mashutikov.semanticAnalysis.model.FeedMessage;

import java.util.List;

@Data
public class NewsAnalysisDto {
    private List<FeedMessage> news;
}
