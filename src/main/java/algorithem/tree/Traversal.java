package algorithem.tree;

import algorithem.tree.nodes.MyTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 遍历树，前序、中序、后序
 * 深度遍历
 * 通过栈或者递归实现
 * 递归方法和迭代方法实现
 */

public class Traversal {

    /**
     * 前序遍历被调方法：中左右
     * 1.使用递归
     * 判断终止条件
     * 确定递归输入参数和返回值
     * 确定单次递归需要进行的操作
     * @param root
     */
    private void preOrderTraversalDigui(MyTreeNode root, List<Integer> result){
        if (root == null){//当前节点为空，直接返回，终止条件
            return;
        }
        result.add(root.val);
        preOrderTraversalDigui(root.left,result);
        preOrderTraversalDigui(root.right,result);
    }

    /**
     * 前序遍历调用方法
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversalByDigui(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        preOrderTraversalDigui(root,result);
        return result;
    }

    /**
     * 2.通过迭代方法实现前序遍历
     * @param root
     */
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

    /**
     * 3.中序遍历递归实现：左中右的顺序
     * @param root
     * @param result
     */
    private void inOrderTraversalDiGui(MyTreeNode root,List<Integer> result){
        if (root == null){
            return;
        }
        inOrderTraversalDiGui(root.left,result);
        result.add(root.val);
        inOrderTraversalDiGui(root.right,result);
    }

    /**
     * 递归实现的中序遍历调用方法
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversalByDigui(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        inOrderTraversalDiGui(root,result);
        return result;
    }

    /**
     * 4.中序遍历的迭代实现
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversalDieDai(MyTreeNode root){
//        中序遍历的迭代方法由于访问节点和处理节点顺序不同，因此需要使用指针遍历访问节点，然后使用栈处理节点
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<MyTreeNode> stack = new Stack<>();
        MyTreeNode cur = root;
        while (cur !=null  || !stack.isEmpty()){
            if (cur!=null){//还没有到达最左边
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 5.递归实现后序遍历：左右中
     * @param root
     * @param result
     */
    private void postTraversalDigui(MyTreeNode root,List<Integer> result){
        if (root==null){
            return;
        }
        postTraversalDigui(root.left,result);
        postTraversalDigui(root.right,result);
        result.add(root.val);
    }
    public List<Integer> postTraversalByDigui(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        postTraversalDigui(root,result);
        return result;
    }

    /**
     * 后序遍历的迭代实现，左右中=中右左的倒序=中左右交换左右迭代的顺序
     * @param root
     * @return
     */
    public List<Integer> postTraversalByDieDai(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<MyTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            MyTreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.left!=null){//左先进，就会先出
                stack.push(pop.left);
            }
            if (pop.right!=null){
                stack.push(pop.right);
            }
        }
        Collections.reverse(result);
        return result;
    }


//    使用统一格式的前中后序迭代方法

    /**
     * 6.1 统一风格前序迭代
     * @param root
     * @return
     */
    public List<Integer> preTraverUniformDieDai(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<MyTreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }

        while (!stack.isEmpty()){
            MyTreeNode peek = stack.peek();
            if (peek!=null){//没到叶子
                stack.pop();//中节点先出栈，最后再进栈，构建统一格式，最后进栈，方便构建右左中->中左右
                //右边先进栈后出栈
                if (peek.right!=null){
                    stack.push(peek.right);
                }
                if (peek.left!=null){
                    stack.push(peek.left);
                }
                stack.push(peek);
                stack.push(null);//多放一个null作为标记，预见null才会正式pop放入结果中。
            }else {
                stack.pop();//null出栈
                MyTreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }
        return result;
    }

    /**
     * 6.2 中序遍历统一格式
     * @param root
     * @return
     */
    public List<Integer> inOrderTraverUniformDieDai(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<MyTreeNode> stack = new Stack<>();
        if (root!=null){
            stack.push(root);
        }

        while (!stack.isEmpty()){
            MyTreeNode peek = stack.peek();
            if (peek!=null){
                stack.pop();
                if (peek.right!=null){
                    stack.push(peek.right);
                }
                stack.push(peek);
                stack.push(null);
                if (peek.left!=null){
                    stack.push(peek.left);
                }
            }else {
                stack.pop();
                MyTreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }
        return result;
    }

    /**
     * 6.3 后序遍历统一格式
     * @param root
     * @return
     */
    public List<Integer> postOrderTraverUniformDieDai(MyTreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<MyTreeNode> stack = new Stack<>();
        if (root!=null){
            stack.push(root);
        }

        while (!stack.isEmpty()){
            MyTreeNode peek = stack.peek();
            if (peek!=null){
                stack.pop();
                stack.push(peek);
                stack.push(null);
                if (peek.right!=null){
                    stack.push(peek.right);
                }
                if (peek.left!=null){
                    stack.push(peek.left);
                }
            }else {
                stack.pop();
                MyTreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }
        return result;
    }



}
