// We could make an implementation of a tree diagram that uses the Iterable<>
// interface so that it could be iterated in a foreach loop
package tree;

import java.util.ArrayList;

// Inheriting the generic type of nested generic class
// https://stackoverflow.com/questions/21566701/nested-class-that-inherits-from-its-generic-parent-class
public final class Node<T> {
    // 't' here is basically the data that the node is storing
    private T t;
    private Node<T> parent;
    private ArrayList<Node<T>> children = new ArrayList<>();

    public Node(T newData) {
        t = newData;
    }

    public Node(T newData, ArrayList<Node<T>> newChildren) {
        t = newData;
        children = newChildren;
    }

    public T value() {
        return t;
    }

    // A smart idea would be to make sure that the node that is being added
    // is not a node elsewhere in the tree, so that a loop doesn't occur
    // I believe that this could be a problem but only if you use a variable
    // for the node assignment, and not constructors.
    public void addNode(Node<T> child) {
        children.add(child);
        child.parent = this;
    }

    public void removeNode(int childIndex) {
        children.remove(childIndex);
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getChild(int childIndex) {
        return children.get(childIndex);
    }

    /*
     An example of this: the array [0, 1, 1]
     would start at the root, then get the first child, then the second child of the root,
     then the second child of the previous node, etc.
     Then the final node would be removed from the tree by removing it from the parent's
     children ArrayList.
     */
    public  Node<T> getNthChild(int[] childrenIndexes) {
        Node<T> child = this;

        for (int i = 0; i < childrenIndexes.length - 1; i++) {
            child = child.children.get(childrenIndexes[i]);
        }

        return child;
    }
}