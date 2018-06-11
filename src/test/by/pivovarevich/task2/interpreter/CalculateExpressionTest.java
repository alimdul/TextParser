package test.by.pivovarevich.task2.interpreter;

import by.pivovarevich.task2.interpreter.CalculateExpression;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculateExpressionTest {

    @Test
    public void calculateExpressionTest() {

        String expectedResult = "-3";

        String result = String.valueOf(new CalculateExpression("6+9*(3-4)").calculate());
        Assert.assertEquals(result, expectedResult);
    }
}
