/*
This class will handle the math part of the model, the addition, multiplcation, exponents, etc.
This class will not handle the splicing of the input string, or the equation tree we came up with.
 */

package calculator.model;

import tree.Node;

/*
 Final here just means that there should not be any
 child classes of Calculator though this could change at some point.
 The "extends (interface)" means that this class will have to implement any method stated
 in the interface.
 */
public final class Calculator implements CalculatorInterface {

    public double Evaluate(String input) {
        try {
            double result = 0;
            Node<String> tree = new Node<>(input);
            tree = SolveForTree(tree, 0);
            return result;
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return -12541.3947687;
    }

    private Node<String> SolveForTree(Node<String> parent, int depth) throws Exception {
       Node<String> root = parent;

       //Split parentheses appart first
       root = ParseForParentheses(root);

       return root;
    }

    private Node<String> ParseForParentheses(Node<String> parent) throws IndexOutOfBoundsException {
        Node<String> root = parent;
        String parentValue = parent.value();
        String subString = "";
        int openParenthesesIndex = 0;

        for (int i = 0; i < parentValue.length(); i++) {
            char currentChar = parentValue.charAt(i);
            if(currentChar == '(') {
                openParenthesesIndex++;
                if(openParenthesesIndex == 1) {
                    if(subString == "") continue;

                    System.out.println(subString + ", OUT P");
                    subString = "";
                }
                else {
                    subString += currentChar;
                }
            }
            else if(currentChar == ')') {
                openParenthesesIndex --;

                if(i + 2 <= parentValue.length()) {
                    subString += parentValue.charAt(i + 1);
                }

                if(openParenthesesIndex == 0) {
                    System.out.println(subString + ", IN P");
                    subString = "";
                }
                else if(openParenthesesIndex < 0) {
                    System.out.println("OH NO");
                    throw new IndexOutOfBoundsException();
                }
            }
            else {
                subString += currentChar;
            }
        }
        return root;
    }


    // The implementation of the method "Add" found in the CalculatorInterface
    public double Add(double a, double b) {
        return a + b;
    }
}