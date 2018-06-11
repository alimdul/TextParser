package by.pivovarevich.task2.interpreter;

import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class ReversePolishNotatation {

    private static final int VAR_I = 2;
    private static final int VAR_J = 4;
    private static final String OPERATORS = "+-*/^";

    public String create(String expression) {

        ArrayDeque<String> operationsStack = new ArrayDeque<>();
        ArrayDeque<String> reversePolishNotatationStack = new ArrayDeque<>();
        String expressionString = "";

        expression = expression.replace(" ", "").replace("(-", "(0-")
                .replace("--i", "(i-1)").replace("i--", "(i-1)")
                .replace("--j", "(j-1)").replace("j--", "(j-1)")
                .replace("++i", "(i+1)").replace("i++", "(i+1)")
                .replace("++j", "(j+1)").replace("j++", "(j+1)")
                .replace("i", String.valueOf(VAR_I)).replace("j", String.valueOf(VAR_J)); //.replace(",-", ",0-");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }

        StringTokenizer stringTokenizer = new StringTokenizer(expression,
                OPERATORS + "()", true);

        while (stringTokenizer.hasMoreTokens()) {

            String token = stringTokenizer.nextToken();

            if (isOpenBracket(token)) {
                operationsStack.push(token);
            } else if (isCloseBracket(token)) {
                while (!operationsStack.isEmpty() && !isOpenBracket(operationsStack.getFirst())) {
                    reversePolishNotatationStack.push(operationsStack.pop());
                }
                operationsStack.pop();

            } else if (isNumber(token)) {
                reversePolishNotatationStack.push(token);
            } else if (isOperator(token)) {
                while (!operationsStack.isEmpty() && isOperator(operationsStack.getFirst())
                        && getPrecedence(token) <= getPrecedence(operationsStack.getFirst())) {
                    reversePolishNotatationStack.push(operationsStack.pop());
                }
                operationsStack.push(token);
            }
        }

        while (!operationsStack.isEmpty()) {
            reversePolishNotatationStack.push(operationsStack.pop());
        }

        for(int i=reversePolishNotatationStack.size(); i>=0; i--) {
            expressionString = expressionString + reversePolishNotatationStack.pollLast() + " ";
        }
        return expressionString;
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isOpenBracket(String token) {
        return "(".equals(token);
    }

    private boolean isCloseBracket(String token) {
        return ")".equals(token);
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private byte getPrecedence(String token) {
        if("+".equals(token) || "-".equals(token)) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
