package mashutikov.semanticAnalysis.model.chart;

import lombok.Data;
import org.apache.commons.math3.util.Precision;

@Data
public class ChartPoint {
    private String name;
    private double x;
    private double y;
    private int r = 1;

    public  ChartPoint(String name, double x, double y, int r) {
        this.name = name;
        this.x = Precision.round(x, 2);
        this.y = Precision.round(y, 2);
        this.r = r;
    }
    public  ChartPoint(String name, double x, double y) {
        this.name = name;
        this.x = Precision.round(x, 2);
        this.y = Precision.round(y, 2);
    }
}
