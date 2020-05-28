package mashutikov.semanticAnalysis.service;

import mashutikov.semanticAnalysis.model.Terms;

public class TermsPreparator {
    private Terms terms = new Terms();
    private PorterStemmer stemmer = new PorterStemmer();
    private static final String[] STOP_WORDS = {
            "-", "еще", "него", "сказать",
            "ж", "нее", "со", "об",
            "без", "же", "ней",	"совсем",
            "более", "жизнь", "нельзя",	"так",
            "больше", "за",	"нет", "такой",
            "будет", "зачем", "ни", "там",
            "будто", "здесь", "нибудь",	"тебя",
            "бы", "никогда", "тем",
            "был", "из", "ним", "теперь",
            "была", "из-за", "них", "то",
            "были", "или", "ничего", "тогда",
            "было", "им", "но", "того",
            "быть", "иногда", "ну", "тоже",
             "их",  "только", "названы",
            "вам",  "об", "том",
            "вас", "кажется", "один", "тот",
            "вдруг", "как", "он", "три",
            "ведь", "какая", "она", "тут",
            "во", "какой", "они", "ты",
            "вот", "когда",	"опять",
            "впрочем", "конечно", "от", "уж",
            "все", "которого", "перед", "уже",
            "всегда", "которые","по", "хорошо",
            "всего", "кто",	"под", "хоть",
            "всех", "куда",	"после","чего",
            "всю", "ли", "потом", "человек",
            "вы", "лучше", "потому", "чем",
             "между", "почти", "через",
            "где", "меня", "при", "что",
            "говорил", "мне", "про", "чтоб",
            "да", "много", "раз", "чтобы",
            "даже", "может", "разве", "чуть",
            "два", "можно", "эти",
            "для", "мой", "сам", "этого",
            "до", "моя", "свое", "этой",
            "другой", "мы",	"свою",	"этом",
            "его", "на", "себе", "этот",
            "ее", "над", "себя", "эту",
            "ей", "надо", "сегодня",
            "ему", "наконец", "сейчас",
            "если", "нас", "сказал",
            "есть",	"не", "сказала"
    };
    public void addTerm(String term) {
        if(isValidWord(term.toLowerCase())){
            String tempTerm =  stemmer.stem(term);
            if(!isDigit(tempTerm)) {
                terms.addTerm(tempTerm);
            }

        }
    }
    public boolean isValidWord(String term) {
        if(term.length() > 1){
            for (String word : STOP_WORDS) {
               if(word.equals(term)){
                   return false;
               }
            }
            return true;
        } else {
            return false;
        }
    }
    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public Terms getTerms(){
        return terms;
    }
}
