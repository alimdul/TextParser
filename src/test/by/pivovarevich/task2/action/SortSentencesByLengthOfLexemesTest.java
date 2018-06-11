package test.by.pivovarevich.task2.action;

import by.pivovarevich.task2.action.SortSentencesByLengthOfLexemes;
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

public class SortSentencesByLengthOfLexemesTest {

    @Test
    public void sortSentencesByLengthOfLexemesTest() {

        List<String> expectedResult = Arrays.asList(" Aa.", " C E D.", " A faa.",
                " Aaaa.", " Bakd ddmkaaa B.");

        String fileName = "data/forSortLexemesAndSentencesTest.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);
            List<TextComponent> sortedSentenceList = new SortSentencesByLengthOfLexemes().sort(textComposite);
            List<String> result = new ArrayList<>();
            for (int i=0; i<sortedSentenceList.size(); i++) {
                result.add(sortedSentenceList.get(i).toString());
            }
            Assert.assertEquals(result, expectedResult);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }
}
