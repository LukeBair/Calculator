package calculator.model.tokens;

import calculator.IllegalCharacterException;
import calculator.model.tree.BinaryNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class TokenParser {

    public BinaryNode<TreeToken> equationTree(String input) throws Exception {
        ArrayList<TreeToken> tokenList = createTokens(input);

        BinaryNode <TreeToken> root = new BinaryNode<>(tokenList.get(0), null, false);
        BinaryNode <TreeToken> previousNode = root;

        for (int i = 1; i < tokenList.size(); i ++) {
            TreeToken currentToken = tokenList.get(i);

            //Becomes child of previous node
            if(currentToken.getPrecendence() > previousNode.getValue().getPrecendence()) {
                if(previousNode.getRightChild() == null) {
                    previousNode.addRightChild(currentToken);
                    previousNode = previousNode.getRightChild();
                }
                else if(previousNode.getLeftChild() == null) {
                    previousNode.addLeftChild(currentToken);
                    previousNode = previousNode.getLeftChild();
                }
                else {
                    throw new Exception();
                }
            }
            //becomes parent of previous node recursion/looping needed
            else {
                //check current nodes parent and compare precedence according to normal rules
                BinaryNode<TreeToken> lastCheckedNode = previousNode;
                boolean keepChecking = true;

                while(lastCheckedNode != null && keepChecking) {
                    if(currentToken.getPrecendence() > lastCheckedNode.getValue().getPrecendence()) {
                        keepChecking = false;

                        if(lastCheckedNode.getIsLeftChild() == true || root == lastCheckedNode) {
                            lastCheckedNode.insertLeftChild(currentToken);

                            previousNode = lastCheckedNode.getLeftChild();

                        } else if(lastCheckedNode.getIsLeftChild() == false) {
                            lastCheckedNode.insertRightChild(currentToken);
                            previousNode = lastCheckedNode.getRightChild();
                        }
                        else {
                            throw new Exception();
                        }
                    }

                    lastCheckedNode = lastCheckedNode.getParent();
                }
                if(lastCheckedNode == null && keepChecking == true) {
                    BinaryNode<TreeToken> tmp = root;
                    root = new BinaryNode<TreeToken>(currentToken, null, false);
                    root.addRightChild(tmp);
                    previousNode = root;
                }
            }
        }

        return root;
    }

    //creates the stack, however it is unorganized
    private @NotNull ArrayList<TreeToken> createTokens(@NotNull String input) throws Exception {
        ArrayList<TreeToken> newList = new ArrayList<>();

        if(input.length() == 0) throw new IndexOutOfBoundsException();

        String currentToken = "";
        int numOpenParentheses = 0;
        boolean previousWasOperator = false;
        boolean previousWasMinus = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (charIsParentheses(currentChar) == 1) {
                numOpenParentheses++;
            }

            if (charIsOperator(currentChar, '-')) {
                if (previousWasOperator) throw new IllegalCharacterException();

                previousWasOperator = true;
            } else previousWasOperator = false;

            if (charIsOperator(currentChar)) {

                if(currentToken != "") {
                    NumberToken newToken = new NumberToken(Double.parseDouble(currentToken));

                    if(previousWasMinus) {//on watch
                        newToken = new NumberToken(-newToken.getValue());
                    }
                    newToken.setPrecendenceModifier(numOpenParentheses * 5);
                    if(input.charAt(i) == '-') {
                        OperatorToken newOp = new OperatorToken('+');

                        if(input.charAt(i + 1) != '(')  {
                            previousWasMinus = true;

                        }
                        else {
                            newOp = new OperatorToken('-');
                        }

                        newList.add(newToken);


                        newOp.setPrecendenceModifier(numOpenParentheses * 5);

                        newList.add(newOp);
                    }
                    else {
                        previousWasMinus = false;
                        newList.add(newToken);

                        OperatorToken newOp = new OperatorToken(currentChar);
                        newOp.setPrecendenceModifier(numOpenParentheses * 5);

                        newList.add(newOp);
                    }

                    currentToken = "";
                }
                else {
                    if(currentChar == '-') {
                        OperatorToken op = new OperatorToken('+');
                        op.setPrecendenceModifier(numOpenParentheses * 5);
                        previousWasMinus = true;
                        newList.add(op);
                    }
                    else {
                        OperatorToken op = new OperatorToken(currentChar);
                        op.setPrecendenceModifier(numOpenParentheses * 5);
                        newList.add(op);
                    }
                }
            } else if(charIsParentheses(currentChar) == -1){
                currentToken += currentChar;
            }

            if(i == input.length() - 1) {
                if(charIsOperator(currentChar)){
                    throw new IllegalCharacterException();
                }
                else if(charIsParentheses(currentChar) == -1) {
                    NumberToken newToken = new NumberToken(Double.parseDouble(currentToken));

                    if(previousWasMinus) {
                        newToken = new NumberToken(-newToken.getValue());
                    }

                    newList.add(newToken);
                }
            }

            if (charIsParentheses(currentChar) == 0) {
                if (numOpenParentheses <= 0)
                    throw new IndexOutOfBoundsException("Number of open and closed parentheses do not match!");
                if(currentToken != "") {
                    NumberToken newToken = new NumberToken(Double.parseDouble(currentToken));

                    if(previousWasMinus) {
                        newToken = new NumberToken(-newToken.getValue());
                    }
                    newToken.setPrecendenceModifier(numOpenParentheses * 5);
                    if(input.charAt(i) == '-') {
                        previousWasMinus = true;
                        newList.add(newToken);

                        OperatorToken newOp = new OperatorToken('+');

                        newOp.setPrecendenceModifier(numOpenParentheses * 5);

                        newList.add(newOp);
                    }
                    else {
                        newList.add(newToken);
                    }

                    currentToken = "";
                }

                numOpenParentheses--;
            }
        }

        if(numOpenParentheses > 0) throw new IndexOutOfBoundsException("Number of open and closed parentheses do not match!");
        return newList;
    }

    private boolean charIsOperator(char charAt) {
        return (charAt == '^' || charAt == '*' || charAt == '/' || charAt == '+' || charAt == '-');
    }

    //for finding negative values which are first in the input (but can be used for other stuff?)
    private boolean charIsOperator(char charAt, char operatorToIgnore) {
        return (
                   ( operatorToIgnore != '^' && charAt == '^' )
                || ( operatorToIgnore != '*' && charAt == '*' )
                || ( operatorToIgnore != '/' && charAt == '/' )
                || ( operatorToIgnore != '+' && charAt == '+' )
                || ( operatorToIgnore != '-' && charAt == '-' )
               );
    }

    private int charIsParentheses(char charAt) {
        if(charAt == '(') {
            return 1;
        }
        else if(charAt == ')'){
            return 0;
        }
        else return -1;
    }
}
