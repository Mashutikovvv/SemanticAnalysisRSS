package mashutikov.semanticAnalysis.analyzer;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import mashutikov.semanticAnalysis.dto.TermDto;
import mashutikov.semanticAnalysis.model.AnalyzedNews;
import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.model.Terms;
import mashutikov.semanticAnalysis.model.chart.ChartPoint;
import mashutikov.semanticAnalysis.model.chart.AnalysisResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Analyzer {

    private List<AnalyzedNews> analyzedNews = new ArrayList<>();
    private Terms commonSetTerms;

    public AnalysisResult executeAnalysis(List<FeedMessage> news) {
        commonSetTerms  = new Terms();
        analyzedNews = new ArrayList<>();
        for (FeedMessage item: news) {
            this.analyzedNews.add(new AnalyzedNews(item));
        }
        for (AnalyzedNews item: analyzedNews) {
            for (String word : item.getTerms().keySet()) {
                commonSetTerms.addTerm(word);
            }
        }
        commonSetTerms.setTerms(commonSetTerms.getTerms().entrySet()
                .stream()
                .filter(term -> term.getValue() > 1 && term.getKey().length() > 1 )
                .collect(Collectors.toMap(term -> term.getKey(), term -> term.getValue())));
        for (AnalyzedNews item: analyzedNews) {
            item.setTerms(item.getTerms().entrySet()
                    .stream()
                    .filter(term -> commonSetTerms.getTerms().get(term.getKey()) != null)
                    .collect(Collectors.toMap(term -> term.getKey(), term -> term.getValue())));
        }
        List<String> terms = new ArrayList<>(commonSetTerms.getTerms().keySet());
        Collections.sort(terms);
        int countCommonTerms = terms.size();
        int countNews = analyzedNews.size();
        double[][] frequencyMatrix = new double[countCommonTerms][countNews];

        for (int i = 0; i < countCommonTerms; i++) {
            for (int j = 0; j < countNews; j++) {
                String word = terms.get(i);
                Integer countTermsInNew = analyzedNews.get(j).getTerms().get(word);
                if(countTermsInNew != null) {
                    frequencyMatrix[i][j] = countTermsInNew;
                }
            }
        }
        SingularValueDecomposition frequencyMatrixsingularDecomposition = new Matrix(frequencyMatrix).svd();
        double[][] u = frequencyMatrixsingularDecomposition.getU().getArray();
        double[][] v = frequencyMatrixsingularDecomposition.getV().getArray();
        List<ChartPoint> termsPoints = new ArrayList<>();
        List<ChartPoint> newsPoints = new ArrayList<>();

        for (int i = 0; i < countCommonTerms; i++) {
            double secondPoint = 0;
            if( u[0].length > 1) {
                secondPoint =  u[i][1];
            }
            termsPoints.add(new ChartPoint(terms.get(i), u[i][0], secondPoint,
                    commonSetTerms.getTerms().get(terms.get(i))));
        }

        for (int i = 0; i < countNews; i++) {
            double secondPoint = 0;
            if( countNews > 1) {
                secondPoint =  v[i][1];
            }
            newsPoints.add(new ChartPoint(analyzedNews.get(i).getTitle(), v[i][0], secondPoint));
        }
        List<TermDto> commonTerms = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : commonSetTerms.getTerms().entrySet()) {
            commonTerms.add( new TermDto(entry.getKey(), entry.getValue()));
        }

        return new AnalysisResult(newsPoints, termsPoints, commonTerms);
    }


}
