package by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;
import by.pivovarevich.task2.interpreter.CalculateExpression;
import by.pivovarevich.task2.composite.CompositeLevel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeOrWordParser implements TextParser {

    private static final String REGEX_LEXEME = "([^\\s]+)";
    private static final String REGEX_WORD = "([a-zA-Z]+-[a-zA-Z]+-[a-zA-Z]+)|([a-zA-Z]+-[a-zA-Z]+)|([^(),:'\\.!?*\\/\\-\\d\\s]+)|(([^\\s]*\\d+[+\\-*\\/]+[^\\s]*))";
    private static final String REGEX_LEXEME_WITH_EXPRESSION = "([^\\s]*\\d+[+\\-*\\/]+[^\\s]*)";

    private SymbolParser symbolParser = new SymbolParser();

    @Override
    public void parse(TextComposite sentence, String sentenceString) throws IncorrectInputParametersException {

        TextComposite lexeme;
        TextComposite word;
        String lexemeString;
        String wordString;
        Pattern patternLexeme = Pattern.compile(REGEX_LEXEME);
        Matcher matcherLexeme = patternLexeme.matcher(sentenceString);
        Pattern patternWord = Pattern.compile(REGEX_WORD);
        Matcher matcherWord;
        Pattern patternLexemeWithExpression = Pattern.compile(REGEX_LEXEME_WITH_EXPRESSION);
        Matcher matcherLexemeWithExpression;

        while (matcherLexeme.find()) {

            lexeme = new TextComposite(CompositeLevel.LEXEME);
            lexemeString = matcherLexeme.group();

            matcherWord = patternWord.matcher(lexemeString);
            if(matcherWord.find()) {
                wordString = matcherWord.group();

                matcherLexemeWithExpression = patternLexemeWithExpression.matcher(wordString);
                if(!matcherLexemeWithExpression.find()) {
                    word = new TextComposite(CompositeLevel.WORD);
                    symbolParser.parse(word, wordString);
                    sentence.add(word);
                }
            }

            matcherLexemeWithExpression = patternLexemeWithExpression.matcher(lexemeString);
            if (matcherLexemeWithExpression.find()) {
                String expressionString = matcherLexemeWithExpression.group();
                String newLexemeString = String.valueOf(new CalculateExpression(expressionString).calculate());
                symbolParser.parse(lexeme, newLexemeString);
            }
            else {
                symbolParser.parse(lexeme, lexemeString);
            }
            sentence.add(lexeme);
        }
    }
}
