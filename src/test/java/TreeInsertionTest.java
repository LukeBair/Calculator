import calculator.model.tree.BinaryNode;
import org.junit.jupiter.api.Test;

public class TreeInsertionTest {

    @Test
    public void Test ()     {
        BinaryNode<Integer> root = new BinaryNode<>(10, null, false);
        root.addRightChild(12);

        root.insertRightChild(11);

        System.out.println(root.getValue());
        System.out.println(root.getRightChild().getValue());

        root.addLeftChild(14);
        root.insertLeftChild(13);

        System.out.println(root.getLeftChild().getValue());
        System.out.println(root.getLeftChild().getLeftChild().getValue());

    }
}
