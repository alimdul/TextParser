package by.pivovarevich.task2.action;

import by.pivovarevich.task2.composite.TextComponent;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortLexemesBySymbol {

    private static final String SYMBOL = "a";

    public List<TextComponent> sort(TextComposite text) {

        List<TextComponent> lexemeList = new ArrayList<>();

        for(TextComponent paragraph: text.getTextComponentList()) {
            if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                TextComposite paragraphComposite = (TextComposite) paragraph;
                for(TextComponent sentence: paragraphComposite.getTextComponentList()) {
                    if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                        TextComposite sentenceComposite = (TextComposite) sentence;
                        for(TextComponent lexeme: sentenceComposite.getTextComponentList()) {
                            if(lexeme.getLevel() == CompositeLevel.LEXEME) {
                                lexemeList.add(lexeme);
                            }
                        }
                    }
                }
            }
        }
        List<TextComponent> result = new ArrayList<>(lexemeList);
        result.sort(comparator.thenComparing(Comparator.comparingInt(lexeme -> findNumberOfSymbol((TextComposite) lexeme))));
        result.sort(Comparator.comparingInt(lexeme -> findNumberOfSymbol((TextComposite) lexeme)));
        List<TextComponent> reverseResult = new ArrayList<>();
        for(int i=result.size()-1; i>=0; i--) {
            reverseResult.add(result.get(i));
        }
        return reverseResult;
    }

    private int findNumberOfSymbol(TextComposite lexeme) {
        int number = 0;
        for(TextComponent leaf: lexeme.getTextComponentList()) {
            if(leaf.getLevel() == CompositeLevel.SYMBOL) {
                if(SYMBOL.equals(leaf.toString())) {
                    number++;
                }
            }
        }
        return number;
    }

    private Comparator<TextComponent> comparator = new Comparator<TextComponent>() {
        @Override
        public int compare(TextComponent o1, TextComponent o2) {

            int size;
            TextComposite o1Composite = (TextComposite) o1;
            TextComposite o2Composite = (TextComposite) o2;
            if(o2Composite.size() >= o1Composite.size()) {
                size = o1Composite.size();
            }
            else {
                size = o2Composite.size();
            }

            for(int i=0; i<size; i++) {
                TextComponent leaf1 = o1Composite.getTextComponentList().get(i);
                TextComponent leaf2 = o2Composite.getTextComponentList().get(i);
                if(leaf2.toString().compareTo(leaf1.toString()) != 0) {
                    return leaf2.toString().compareTo(leaf1.toString());
                }
            }
            return 0;
        }
    };
}
