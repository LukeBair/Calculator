// We could make an implementation of a tree diagram that uses the Iterable<>
// interface so that it could be iterated in a foreach loop
package tree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

// The <T> is the syntax to state that this tree can be of any type
// and it is called a "Generic Class"
public final class GenericTree<T> {

    // Inheriting the generic type of nested generic class
    // https://stackoverflow.com/questions/21566701/nested-class-that-inherits-from-its-generic-parent-class
    public final class Node {
        // 't' here is basically the data that the node is storing
        public T t;
        public ArrayList<Node> children = new ArrayList<>();

        public Node (T newData) {
            t = newData;
        }

        public Node (T newData, ArrayList<Node> newChildren) {
            t = newData;
            children = newChildren;
        }

        // A smart idea would be to make sure that the node that is being added
        // is not a node elsewhere in the tree, so that a loop doesn't occur
        // I believe that this could be a problem but only if you use a variable
        // for the node assignment, and not constructors.
        public void AddNode(Node t) {
            t.children.add(t);
        }
    }

    public Node root;

    public GenericTree(Node newRoot) {
        root = newRoot;
    }

    /*
     An example of this: the array [0, 1, 1]
     would start at the root, then get the first child, then the second child of the root,
     then the second child of the previous node, etc.
     Then the final node would be removed from the tree by removing it from the parent's
     children ArrayList.
     */
    public void RemoveNodeFromRoot(int[] indexes) {
        TravelToNodeFromRoot(indexes, indexes.length - 2).children.remove(indexes[indexes.length - 1]);
    }

    public void RemoveNodeFromNode(Node startingNode, int[] indexes) {
        TravelToNodeFromNode(startingNode, indexes, indexes.length - 2).children.remove(indexes[indexes.length - 1]);
    }

    /*
     @NotNull is an annotation, and says that the following parameter can not be null
     @Nullable means that the following parameter can be null
     Though libraries that use the ConstraintValidator type reference
     can use the annotations for whatever the library needs
     https://stackoverflow.com/questions/34094039/using-notnull-annotation-in-method-argument\
     */
    public Node TravelToNodeFromRoot(@NotNull int [] indexes, int finalIndex) {
        // For every int in the int[] indexes
        Node nextNode = root;

        // 'i' is the index of the int[] "indexes" while indexesValue is the value
        // of the int[] indexes at position i
        for (int i = 0; i < finalIndex; i++) {
            nextNode = nextNode.children.get(indexes[i]);
        }
        return nextNode;
    }

    public Node TravelToNodeFromNode(@NotNull Node startingNode, @NotNull int [] indexes, int finalIndex) {
        // For every int in the int[] indexes
        Node nextNode = startingNode;

        // 'i' is the index of the int[] "indexes" while indexesValue is the value
        // of the int[] indexes at position i
        for (int i = 0; i < finalIndex; i++) {
            nextNode = nextNode.children.get(indexes[i]);
        }
        return nextNode;
    }
}
