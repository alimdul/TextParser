package by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements TextParser {

    private static final String REGEX_PARAGRAPH = "\\t.+\\n*[^\\t]+";

    private SentenceParser sentenceParser = new SentenceParser();

    @Override
    public void parse(TextComposite wholeText, String text) throws IncorrectInputParametersException {

        TextComposite paragraph;
        String paragraphString;
        Pattern patternParagraph = Pattern.compile(REGEX_PARAGRAPH);
        Matcher matcher = patternParagraph.matcher(text);
        while (matcher.find()) {
            paragraphString = matcher.group();
            paragraph = new TextComposite(CompositeLevel.PARAGRAPH);
            sentenceParser.parse(paragraph, paragraphString);
            wholeText.add(paragraph);
        }
    }
}
