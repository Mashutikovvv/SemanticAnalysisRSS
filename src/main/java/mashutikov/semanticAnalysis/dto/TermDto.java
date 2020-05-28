package mashutikov.semanticAnalysis.dto;

import lombok.Data;

@Data
public class TermDto {
    private String word;
    private Integer count;
    public TermDto(String word, int count) {
        this.word = word;
        this.count = count;
    }
}
