package mashutikov.semanticAnalysis.controller;

import lombok.RequiredArgsConstructor;
import mashutikov.semanticAnalysis.analyzer.Analyzer;
import mashutikov.semanticAnalysis.dto.NewsAnalysisDto;
import mashutikov.semanticAnalysis.dto.ResourcesDto;
import mashutikov.semanticAnalysis.model.FeedMessage;
import mashutikov.semanticAnalysis.service.NewsFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class NewsController {

    @Autowired
    private NewsFetcher fetcher;

    @PostMapping("/fetchNews")
    public List<FeedMessage> fetchNews(@RequestBody ResourcesDto dto) {
        return fetcher.fetchNews(dto.getUrls());
    }
    @PostMapping("/getAnalysis")
    public List<FeedMessage> getAnalysis(@RequestBody NewsAnalysisDto dto) {
        Analyzer analyzer = new Analyzer(dto.getNews());
        System.out.println(analyzer.executeAnalysis());
        return null;
    }

}
