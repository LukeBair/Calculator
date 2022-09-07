/*
This class will handle the math part of the model, the addition, multiplcation, exponents, etc.
This class will not handle the splicing of the input string, or the equation tree we came up with.
 */

package calculator.model;

import calculator.model.tokens.NumberToken;
import calculator.model.tokens.OperatorToken;
import calculator.model.tokens.TokenParser;
import calculator.model.tokens.TreeToken;
import calculator.model.tree.BinaryNode;
/*
 Final here just means that there should not be any
 child classes of Calculator though this could change at some point.
 The "extends (interface)" means that this class will have to implement any method stated
 in the interface.
 */
public final class Calculator implements CalculatorInterface {

    public Double evaluate(String input) {
        try {
            TokenParser tokenParser = new TokenParser();
            BinaryNode<TreeToken> parsedTree = tokenParser.equationTree(input);

            //Solve!
            if(parsedTree.getValue() instanceof NumberToken) return ((NumberToken) parsedTree.getValue()).getValue();

            return solve(parsedTree);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //for debugs
    public void printChildValues(BinaryNode<TreeToken> root) {
        if(root.getLeftChild() != null){
            printChildValues(root.getLeftChild());
        }
        if(root.getRightChild() != null) {
            printChildValues(root.getRightChild());
        }

        System.out.println(root.getValue().getTokenValue());
    }

    // The implementation of the method "Add" found in the CalculatorInterface
    public double Add(double a, double b) {
        return a + b;
    }

    public Double solve(BinaryNode<TreeToken> root) {
        Double val = new Double(0);
        if(root.getValue() instanceof NumberToken) {
            val = ((NumberToken) root.getValue()).getValue();
            return val;
        }

        if(root.getLeftChild().getValue() instanceof OperatorToken) {
            root.setLeftChild(new BinaryNode<>(new NumberToken(solve(root.getLeftChild())), root, true));
        }
        if(root.getRightChild().getValue() instanceof OperatorToken) {
            root.setRightChild(new BinaryNode<>(new NumberToken(solve(root.getRightChild())), root, false));
        }

        Double leftVal = ((NumberToken)root.getLeftChild().getValue()).getValue();
        Double rightVal = ((NumberToken)root.getRightChild().getValue()).getValue();

        switch (((OperatorToken) root.getValue()).getOperator()) {
            case EXPONENT:
                val = Math.pow(rightVal.doubleValue(), leftVal.doubleValue());
                break;
            case MULTIPLICATION:
                val = rightVal * leftVal;
                break;
            case DIVISION:
                val = rightVal / leftVal;
                break;
            case ADDITION:
                val = rightVal + leftVal;
                break;
            case SUBTRACTION:
                val = rightVal -  leftVal;
                break;
        }
        return val;
    }
}