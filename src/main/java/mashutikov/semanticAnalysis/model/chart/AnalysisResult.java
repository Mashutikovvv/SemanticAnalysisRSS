package mashutikov.semanticAnalysis.model.chart;

import lombok.Data;
import mashutikov.semanticAnalysis.dto.TermDto;
import mashutikov.semanticAnalysis.model.Terms;

import java.util.List;

@Data
public class AnalysisResult {
    private List<ChartPoint> news;
    private List<ChartPoint> terms;
    private List<TermDto> commonTerms;

    public AnalysisResult(List<ChartPoint> news, List<ChartPoint> terms, List<TermDto> commonTerms){
        this.news = news;
        this.terms = terms;
        this.commonTerms = commonTerms;
    }
}
