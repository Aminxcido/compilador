package org.xtext.example.mathlang.validation;

import org.eclipse.xtext.validation.Check;
import org.xtext.example.mathlang.mathLang.*;

public class MathLangValidator extends AbstractMathLangValidator {
    
    public static final String DIV_BY_ZERO = "divByZero";
    public static final String INVALID_FRAC_ARGS = "invalidFracArgs";
    public static final String INVALID_ASSIGNMENT = "invalidAssignment";

    @Check
    public void checkDivisionByZero(Div div) {
        if (div.getRight() instanceof NumberLiteral) {
            NumberLiteral num = (NumberLiteral) div.getRight();
            if (num.getValue() == 0) {
                error("Division by zero is not allowed",
                      null,
                      DIV_BY_ZERO);
            }
        }
    }

    @Check
    public void checkFunctionArgs(FunctionCall func) {
        if ("frac".equals(func.getFunc())) {
            if (func.getArgs().size() != 2) {
                error("frac requires exactly two arguments separated by comma",
                     null,
                     INVALID_FRAC_ARGS);
            }
        }
    }

    @Check
    public void checkAssignment(Assignment assign) {
        if (!(assign.getLeft() instanceof Variable)) {
            error("Left side of assignment must be a variable",
                  null,
                  INVALID_ASSIGNMENT);
        }
    }
}