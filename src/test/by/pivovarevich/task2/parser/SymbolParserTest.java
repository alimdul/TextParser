package test.by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.composite.Symbol;
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

public class SymbolParserTest {

    @Test
    public void symbolInLexemeParserTest() {

        List<String> expectedNumbers = Arrays.asList("2", "2", "1", "5", "11", "5", "4");

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
                            TextComposite sentenceComposite = (TextComposite) sentence;
                            for(TextComponent lexeme: sentenceComposite.getTextComponentList()) {
                                if(lexeme.getLevel() == CompositeLevel.LEXEME) {
                                    int number = 0;
                                    TextComposite lexemeComposite = (TextComposite) lexeme;
                                    for(TextComponent symbol: lexemeComposite.getTextComponentList()) {
                                        if(symbol.getLevel() == CompositeLevel.SYMBOL) {
                                            number++;
                                        }
                                    }
                                    numbersOfLexemes.add(String.valueOf(number));
                                }
                            }
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
    public void symbolInWordParserTest() {

        List<String> expectedNumbers = Arrays.asList("2", "2", "1", "11", "4", "3");

        String fileName = "data/simpleText.txt";
        File file = new File(fileName);
        try {
            String wholeText = new ReadText().read(file);
            TextComposite textComposite = new TextComposite(CompositeLevel.TEXT);
            new WholeTextParser().parse(textComposite, wholeText);

            List<String> numbersOfSymbols = new ArrayList<>();
            for(TextComponent paragraph: textComposite.getTextComponentList()) {
                if(paragraph.getLevel() == CompositeLevel.PARAGRAPH) {
                    TextComposite paragraphComposite = (TextComposite) paragraph;
                    for(TextComponent sentence: paragraphComposite.getTextComponentList()) {
                        if(sentence.getLevel() == CompositeLevel.SENTENCE) {
                            TextComposite sentenceComposite = (TextComposite) sentence;
                            for(TextComponent word: sentenceComposite.getTextComponentList()) {
                                if(word.getLevel() == CompositeLevel.WORD) {
                                    int number = 0;
                                    TextComposite wordComposite = (TextComposite) word;
                                    for(TextComponent symbol: wordComposite.getTextComponentList()) {
                                        if(symbol.getLevel() == CompositeLevel.SYMBOL) {
                                            number++;
                                        }
                                    }
                                    numbersOfSymbols.add(String.valueOf(number));
                                }
                            }
                        }
                    }
                }
            }
            Assert.assertEquals(numbersOfSymbols, expectedNumbers);
        } catch (IncorrectInputFileException | IncorrectInputParametersException e) {
            Assert.fail("Unexpected fail!");
        }
    }

    @Test(expectedExceptions = IncorrectInputParametersException.class)
    public void symbolParserExceptionsTest() throws IncorrectInputParametersException {

        String leafString = "ab";
        new Symbol(leafString, CompositeLevel.SYMBOL);
    }
}
