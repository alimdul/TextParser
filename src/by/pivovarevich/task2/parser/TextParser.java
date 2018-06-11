package by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;

public interface TextParser {

    void parse(TextComposite textComponent, String text) throws IncorrectInputParametersException;
}
