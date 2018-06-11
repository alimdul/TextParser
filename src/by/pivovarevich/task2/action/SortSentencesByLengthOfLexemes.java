package by.pivovarevich.task2.action;

import by.pivovarevich.task2.composite.TextComponent;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortSentencesByLengthOfLexemes {

    public List<TextComponent> sort(TextComposite text) {

        List<TextComponent> sentenceList = new ArrayList<>();
        for(TextComponent paragraph: text.getTextComponentList()) {
            if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                TextComposite paragraphComposite = (TextComposite) paragraph;
                for(TextComponent sentence: paragraphComposite.getTextComponentList()) {
                    if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                        sentenceList.add(sentence);
                    }
                }
            }
        }
        List<TextComponent> result = new ArrayList<>(sentenceList);
        result.sort(Comparator.comparingInt(sentence -> findLengthOfLexemes((TextComposite) sentence)));

        return result;
    }

    private int findLengthOfLexemes(TextComposite sentence) {
        int length = 0;
        for(TextComponent lexeme: sentence.getTextComponentList()) {
            if(lexeme.getLevel() == CompositeLevel.LEXEME) {
                TextComposite lexemeComposite = (TextComposite) lexeme;
                for(TextComponent leaf: lexemeComposite.getTextComponentList()) {
                    if(leaf.getLevel() == CompositeLevel.SYMBOL) {
                        length++;
                    }
                }
            }
        }
        return length;
    }
}
