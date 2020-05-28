package mashutikov.semanticAnalysis.service.apiClient;

import mashutikov.semanticAnalysis.model.Feed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "apiClient",  url = "http://newsapi.org/v2")
public interface ApiClient {
    @RequestMapping(method = GET, value = "/everything?apiKey=cdde01a20f9d4e35853a2a1a2d223c15&pageSize=100", consumes = APPLICATION_JSON_VALUE)
    Feed getNews(@RequestParam("sources") final String[] sources, @RequestParam("language") final String language,
                 @RequestParam("q") final String q, @RequestParam("from") final String from,
                 @RequestParam("to") final String to);
}
