package by.pivovarevich.task2.action;

import by.pivovarevich.task2.composite.TextComponent;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortParagraphsByNumberOfSentences {

    public List<TextComponent> sort(TextComposite text) {

        List<TextComponent> paragraphList = new ArrayList<>();
        for(TextComponent paragraph: text.getTextComponentList()) {
            if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                paragraphList.add(paragraph);
            }
        }
        List<TextComponent> result = new ArrayList<>(paragraphList);
        result.sort(Comparator.comparingInt(paragraph -> findNumberOfSentences((TextComposite) paragraph)));

        return result;
    }

    private int findNumberOfSentences(TextComposite paragraph) {
        int count = 0;
        for(TextComponent sentence: paragraph.getTextComponentList()) {
            if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                count++;
            }
        }
        return count;
    }
}