package test.by.pivovarevich.task2.action;

import by.pivovarevich.task2.action.SortParagraphsByNumberOfSentences;
import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.composite.TextComponent;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.exception.IncorrectInputFileException;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;
import by.pivovarevich.task2.parser.WholeTextParser;
import by.pivovarevich.task2.reader.ReadText;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortParagraphsByNumberOfSentencesTest {

    @Test
    public void sortParagraphsByNumberOfSentencesTest() {

        List<String> expectedResult = Arrays.asList(" Hello.", " Hello Hello.", " Hello Hello Hello. Hello.",
                " Hello Hello. Hello. Hello Hello.");

        String fileName = "data/forSortParagraphsByNumberOfSentencesTest.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);
            List<TextComponent> sortedParagraphList = new SortParagraphsByNumberOfSentences().sort(textComposite);
            List<String> result = new ArrayList<>();
            for (int i=0; i<sortedParagraphList.size(); i++) {
                result.add(sortedParagraphList.get(i).toString());
            }
            Assert.assertEquals(result, expectedResult);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }
}
