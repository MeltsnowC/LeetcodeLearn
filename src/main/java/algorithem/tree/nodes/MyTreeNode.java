package algorithem.tree.nodes;

//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor

/**
 * 二叉树
 */
public class MyTreeNode {
    public int val;
    public MyTreeNode left;
    public MyTreeNode right;
    public MyTreeNode(){}

    public MyTreeNode(int val){this.val = val;}

    public MyTreeNode(int val,MyTreeNode left,MyTreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
