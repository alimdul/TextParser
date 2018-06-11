package test.by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.exception.IncorrectInputFileException;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;
import by.pivovarevich.task2.parser.WholeTextParser;
import by.pivovarevich.task2.reader.ReadText;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ParagraphParserTest {

    @Test
    public void paragraphParserTest() {

        int expectedSize = 4;

        String fileName = "data/text.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);
            Assert.assertEquals(textComposite.size(), expectedSize);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }
}
