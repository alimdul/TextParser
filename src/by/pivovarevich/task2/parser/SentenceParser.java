package by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {

    private static final String REGEX_SENTENCE = "([A-Z\\d]{1}[^.!?]*\\.{3})|([A-Z\\d]{1}[^.!?]*[.!?.]{1})";

    private LexemeOrWordParser lexemeOrWordParser = new LexemeOrWordParser();

    @Override
    public void parse(TextComposite paragraph, String paragraphString) throws IncorrectInputParametersException {

        TextComposite sentence;
        String sentenceString;
        Pattern patternSentence = Pattern.compile(REGEX_SENTENCE);
        Matcher matcher = patternSentence.matcher(paragraphString);
        while (matcher.find()) {
            sentenceString = matcher.group();
            sentence = new TextComposite(CompositeLevel.SENTENCE);
            lexemeOrWordParser.parse(sentence, sentenceString);
            paragraph.add(sentence);
        }
    }
}
