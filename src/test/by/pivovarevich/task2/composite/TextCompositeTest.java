package test.by.pivovarevich.task2.composite;

import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.exception.IncorrectInputFileException;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;
import by.pivovarevich.task2.parser.WholeTextParser;
import by.pivovarevich.task2.reader.ReadText;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TextCompositeTest {

    @Test
    public void textCompositeToStringTest() {

        String expectedText = "\t It has survived - not only (five) centuries, but also the leap into 14 electronic typesetting, remaining 8 essentially -3 unchanged. It was popularised in the 605 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\t It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using 204 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "\t It is a -6000 established fact that a reader will be of a page when looking at its layout.\n" +
                "\t Bye.\n";

        String fileName = "data/text.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);
            String newText = textComposite.toString();
            Assert.assertEquals(newText, expectedText);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }
}
