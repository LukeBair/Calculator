import calculator.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.Node;

public class TreeTest {
    @Test
    void rootChildRefersToRootAsParent() {
        Node<String> root = new Node<>("This is the Root");
        root.addNode(new Node<>("First Child"));
        Assertions.assertEquals(root, root.getChild(0).getParent(), "Child had wrong parent");
    }

    @Test
    public void TestTheTree() {
        Node<String> root = new Node<>("This is the Root");
        root.addNode(new Node<>("First Child"));
        root.getChild(0).addNode(new Node<>("First Grandson"));
        root.getNthChild(new int[] {0, 0}).addNode(new Node<>("First Great Grandson"));
        root.getChild(0).addNode(new Node<>("Second Grandchild"));

        System.out.println("Root value: " + root.value());
        System.out.println("First Great Grandson: " + root.getNthChild(new int[] {0, 0, 0}).value());
        System.out.println("Second Grandchild: " + root.getChild(0).getChild(0).value());

        Assertions.assertEquals(root, root.getChild(0).getParent(), "Child had wrong parent");
    }
}
