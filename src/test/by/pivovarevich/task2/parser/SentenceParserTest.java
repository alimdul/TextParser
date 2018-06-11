package test.by.pivovarevich.task2.parser;

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

public class SentenceParserTest {

    @Test
    public void sentenceParserTest() {

        List<String> expectedNumbers = Arrays.asList("2", "2", "1", "1");

        String fileName = "data/text.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);

            List<String> numbersOfSentences = new ArrayList<>();
            for(TextComponent paragraph: textComposite.getTextComponentList()) {
                if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                    TextComposite paragraphComposite = (TextComposite) paragraph;
                    int number = 0;
                    for(TextComponent sentence: paragraphComposite.getTextComponentList()) {
                        if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                            number++;
                        }
                    }
                    numbersOfSentences.add(String.valueOf(number));
                }
            }
            Assert.assertEquals(numbersOfSentences, expectedNumbers);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }
}
