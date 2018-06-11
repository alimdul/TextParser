package by.pivovarevich.task2.interpreter;

import by.pivovarevich.task2.interpreter.expression.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculateExpression {

    private ArrayList<MathExpression> listExpression;

    public CalculateExpression(String expression) {
        listExpression = new ArrayList<>();
        String polishNotatation = new ReversePolishNotatation().create(expression);
        parse(polishNotatation);
    }

    public void parse(String expression) {

        for(String lexeme: expression.split("\\p{Blank}+")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp) {
                case '+':
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if(scanner.hasNextInt()) {
                        listExpression.add(new NonterminalExpressionNumber(scanner.nextInt()));
                    }
            }
        }
    }

    public Integer calculate() {

        Context context = new Context();
        for(MathExpression terminal: listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}
