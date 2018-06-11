package by.pivovarevich.task2.composite;

import by.pivovarevich.task2.exception.IncorrectInputParametersException;

public class Symbol implements  TextComponent {

    private static final int ONE_SYMBOL = 1;

    private String symbol;
    private CompositeLevel level;

    public Symbol(String symbol, CompositeLevel level) throws IncorrectInputParametersException {
        if(symbol.length() == ONE_SYMBOL) {
            this.symbol = symbol;
            this.level = level;
        }
        else {
            throw new IncorrectInputParametersException("A symbol can not contain more than one character");
        }
    }

    @Override
    public CompositeLevel getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
