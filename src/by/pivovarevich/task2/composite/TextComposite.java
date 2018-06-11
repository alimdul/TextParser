package by.pivovarevich.task2.composite;

import java.util.LinkedList;
import java.util.List;

public class TextComposite implements TextComponent {

    private List<TextComponent> textComponentList = new LinkedList<>();
    private CompositeLevel level;

    public TextComposite(CompositeLevel level) {
        this.level = level;
    }

    public List<TextComponent> getTextComponentList() {
        return textComponentList;
    }

    public int size() {
        return textComponentList.size();
    }

    public void add(TextComponent textComponent) {
        textComponentList.add(textComponent);
    }

    @Override
    public CompositeLevel getLevel() {
        return level;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();

        for (TextComponent component : textComponentList) {
            if (component.getLevel() == CompositeLevel.PARAGRAPH) {
                resultString.append("\t");
            }
            if (component.getLevel() == CompositeLevel.LEXEME) {
                resultString.append(" ");
            }
            if(component.getLevel() != CompositeLevel.WORD) {
                resultString.append(component.toString());
            }
            if(component.getLevel() == CompositeLevel.PARAGRAPH) {
                resultString.append("\n");
            }
        }
        return resultString.toString();
    }
}
