package by.pivovarevich.task2.action;

import by.pivovarevich.task2.composite.TextComponent;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortSentencesByLengthOfWords {

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
        result.sort(Comparator.comparingInt(sentence -> findLengthOfWords((TextComposite) sentence)));

        return result;
    }

    private int findLengthOfWords(TextComposite sentence) {
        int length = 0;
        for(TextComponent word: sentence.getTextComponentList()) {
            if(word.getLevel() == CompositeLevel.WORD) {
                TextComposite wordComposite = (TextComposite) word;
                for(TextComponent leaf: wordComposite.getTextComponentList()) {
                    if(leaf.getLevel() == CompositeLevel.SYMBOL) {
                        length++;
                    }
                }
            }
        }
        return length;
    }
}
