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

public class LexemeOrWordParserTest {

    @Test
    public void lexemeParserTest() {

        List<String> expectedNumbers = Arrays.asList("6", "1");

        String fileName = "data/simpleText.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);

            List<String> numbersOfLexemes = new ArrayList<>();
            for(TextComponent paragraph: textComposite.getTextComponentList()) {
                if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                    TextComposite paragraphComposite = (TextComposite) paragraph;
                    for(TextComponent sentence: paragraphComposite.getTextComponentList()) {
                        if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                            int number = 0;
                            TextComposite sentenceComposite = (TextComposite) sentence;
                            for(TextComponent lexeme: sentenceComposite.getTextComponentList()) {
                                if(lexeme.getLevel() == CompositeLevel.LEXEME) {
                                    number++;
                                }
                            }
                            numbersOfLexemes.add(String.valueOf(number));
                        }
                    }
                }
            }
            Assert.assertEquals(numbersOfLexemes, expectedNumbers);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }

    @Test
    public void wordParserTest() {

        List<String> expectedNumbers = Arrays.asList("5", "1");

        String fileName = "data/simpleText.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);

            List<String> numbersOfWords = new ArrayList<>();
            for(TextComponent paragraph: textComposite.getTextComponentList()) {
                if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                    TextComposite paragraphComposite = (TextComposite) paragraph;
                    for(TextComponent sentence: paragraphComposite.getTextComponentList()) {
                        if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                            int number = 0;
                            TextComposite sentenceComposite = (TextComposite) sentence;
                            for(TextComponent lexeme: sentenceComposite.getTextComponentList()) {
                                if(lexeme.getLevel() == CompositeLevel.WORD) {
                                    number++;
                                }
                            }
                            numbersOfWords.add(String.valueOf(number));
                        }
                    }
                }
            }
            Assert.assertEquals(numbersOfWords, expectedNumbers);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }
}
