package algorithem.tree.nodes;

/**
 * 完美二叉树结构
 */
public class Perfect2Node {
    public int val;
    public Perfect2Node left;
    public Perfect2Node right;
    public Perfect2Node next;

    public Perfect2Node() {}

    public Perfect2Node(int _val) {
        val = _val;
    }

    public Perfect2Node(int _val, Perfect2Node _left, Perfect2Node _right, Perfect2Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
