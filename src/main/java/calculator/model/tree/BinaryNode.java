package calculator.model.tree;

import calculator.IllegalCharacterException;

public class BinaryNode<T> {
    private final T value;
    private BinaryNode<T> leftChild, rightChild;
    private BinaryNode<T> parent;
    private boolean isLeftChild;
    public T getValue() { return value; }

    public BinaryNode(T newValue, BinaryNode<T> parent, boolean isLeft) {
        value = newValue;
        this.parent = parent;
        isLeftChild = isLeft;
    }

    public void addRightChild(T t) {
        if(rightChild != null) throw new IndexOutOfBoundsException();

        BinaryNode<T> newChild = new BinaryNode<T>(t, this, false);
        this.rightChild = newChild;
    }

    public void addRightChild(BinaryNode<T> t) {
        if(rightChild != null) throw new IndexOutOfBoundsException();

        t.setParent(this);
        t.setIsLeft(false);

        this.rightChild = t;
    }

    public void addLeftChild(T t ) {
        if(leftChild != null) throw new IndexOutOfBoundsException();

        BinaryNode<T> newChild = new BinaryNode<T>(t, this, true);
        this.leftChild = newChild;
    }

    public void addLeftChild(BinaryNode<T> t ) {
        if(leftChild != null) throw new IndexOutOfBoundsException();

        t.setParent(this);
        t.setIsLeft(true);
        this.leftChild = t;
    }

    public void setRightChild(BinaryNode<T> node) {

        this.rightChild = node;
    }

    public void setLeftChild(BinaryNode<T> node) {

        this.leftChild = node;
    }

    public BinaryNode<T> getLeftChild() { return leftChild; }
    public BinaryNode<T> getRightChild() { return rightChild; }
    public BinaryNode<T> getParent() { return parent; }
    public void setParent(BinaryNode<T> newParent) { this.parent = newParent; }
    public void removeLeftChild() { leftChild = null; }
    public void removeRightChild() { rightChild = null; }
    public void setIsLeft(boolean isLeft) { isLeftChild = isLeft; }
    public boolean getIsLeftChild() { return isLeftChild; }

    public BinaryNode<T> getChildRecursively(String path) throws IllegalCharacterException {
        BinaryNode<T> node = this;
        
        if(path.length() == 1) return this;

        for (int i = 0; i < path.length(); i++) {
            if(path.charAt(i) == '0') {
                node = node.getLeftChild();
            } else if (path.charAt(i) == '1') {
                node = node.getRightChild();
            } else {
                throw new IllegalCharacterException();
            }
        }
        return node;
    }

    public void insertLeftChild(T newToken) {
        BinaryNode<T> tmp = getLeftChild();

        removeLeftChild();
        addLeftChild(newToken);

        if(leftChild.getRightChild() == null) {
            leftChild.setRightChild(tmp);
        }
        else {
            leftChild.setLeftChild(tmp);
        }
    }

    public void insertRightChild(T newToken) {
        BinaryNode<T> tmp = getRightChild();
        removeRightChild();

        addRightChild(newToken);
        rightChild.setRightChild(tmp);
    }
}
