package test.by.pivovarevich.task2.reader;

import by.pivovarevich.task2.exception.IncorrectInputFileException;
import by.pivovarevich.task2.reader.ReadText;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ReadTextTest {

    @Test
    public void readFromFileEqualStringTest() {

        String expectedText = "Hello, World!";

        String fileName = "data/forReadFileTest.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            Assert.assertEquals(wholeText, expectedText);
        } catch (IncorrectInputFileException e) {
            Assert.fail("Unexpected fail!");
        }
    }

    @Test(expectedExceptions = IncorrectInputFileException.class)
    public void readFromFileIsNullExceptionTest() throws IncorrectInputFileException {

        File file = null;
        new ReadText().read(file);
    }

    @Test(expectedExceptions = IncorrectInputFileException.class)
    public void readFromFileIsNotExistExceptionTest() throws IncorrectInputFileException {

        String FILE = "data/wrongFile.txt";
        File file = new File(FILE);
        new ReadText().read(file);
    }

    @Test(expectedExceptions = IncorrectInputFileException.class)
    public void readFromFileIsEmptyExceptionTest() throws IncorrectInputFileException {

        String FILE = "data/empty.txt";
        File file = new File(FILE);
        new ReadText().read(file);
    }
}
