package by.pivovarevich.task2.parser;

import by.pivovarevich.task2.composite.TextComposite;
import by.pivovarevich.task2.exception.IncorrectInputParametersException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WholeTextParser implements TextParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private ParagraphParser paragraphParser = new ParagraphParser();

    @Override
    public void parse(TextComposite wholeText, String text) throws IncorrectInputParametersException {

        paragraphParser.parse(wholeText, text);
        LOGGER.log(Level.INFO, "- Text is successfully taken apart.");
    }
}
