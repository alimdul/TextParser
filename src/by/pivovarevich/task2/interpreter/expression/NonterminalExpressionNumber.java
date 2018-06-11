package by.pivovarevich.task2.interpreter.expression;

import by.pivovarevich.task2.interpreter.Context;

public class NonterminalExpressionNumber implements MathExpression {

    private int number;

    public NonterminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
