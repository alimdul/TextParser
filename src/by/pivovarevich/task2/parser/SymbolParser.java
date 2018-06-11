package by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.Symbol;
import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.composite.CompositeLevel;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser implements TextParser {

    private static final String REGEX_LEAF = "(\\w)|([^\\w\\s])";

    @Override
    public void parse(TextComposite lexemeOrWord, String lexemeOrWordString) throws IncorrectInputParametersException {

        Symbol symbol;
        String leafString;
        Pattern patternLeaf = Pattern.compile(REGEX_LEAF);
        Matcher matcherSymbol = patternLeaf.matcher(lexemeOrWordString);

        for(int i=0; i<lexemeOrWordString.length(); i++) {
            if (matcherSymbol.find()) {
                leafString = matcherSymbol.group();
                symbol = new Symbol(leafString, CompositeLevel.SYMBOL);
                lexemeOrWord.add(symbol);
            }
        }
    }
}
