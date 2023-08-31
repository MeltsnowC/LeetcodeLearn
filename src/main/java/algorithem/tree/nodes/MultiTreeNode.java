package algorithem.tree.nodes;

import java.util.List;

/**
 * N叉树
 */
public class MultiTreeNode {
    public int val;
    public List<MultiTreeNode> children;
    public MultiTreeNode(){}
    public MultiTreeNode(int val){
        this.val = val;
    }

    public MultiTreeNode(int val,List<MultiTreeNode> children){
        this.val = val;
        this.children = children;
    }

}
