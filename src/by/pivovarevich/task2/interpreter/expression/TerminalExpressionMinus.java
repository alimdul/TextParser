package by.pivovarevich.task2.interpreter.expression;

import by.pivovarevich.task2.interpreter.Context;

public class TerminalExpressionMinus implements MathExpression {

    @Override
    public void interpret(Context context) {
        int temporaryValue = context.popValue();
        context.pushValue(context.popValue() - temporaryValue);
    }
}
