package algorithem.tree;

import algorithem.tree.nodes.MyTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//臭傻逼
public class Main {
    public static void main(String[] args) {
        MyTreeNode root = new MyTreeNode();
        Main ob = new Main();
        ob.preOrderTraversalDieDai(root);

    }
    public List<Integer> preOrderTraversalDieDai(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<MyTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
//            按照中右左的顺序放入栈中，这样输出才能表示为中左右
//            先处理在入栈
            MyTreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.right!=null){
                stack.push(pop.right);
            }
            if (pop.left!=null){
                stack.push(pop.left);
            }
        }
        return result;
    }
}
