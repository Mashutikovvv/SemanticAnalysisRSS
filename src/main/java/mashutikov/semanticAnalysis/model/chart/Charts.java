package mashutikov.semanticAnalysis.model.chart;

import lombok.Data;
import java.util.List;

@Data
public class Charts {
    private List<ChartPoint> news;
    private List<ChartPoint> terms;

    public Charts(List<ChartPoint> news, List<ChartPoint> terms){
        this.news = news;
        this.terms = terms;
    }
}
