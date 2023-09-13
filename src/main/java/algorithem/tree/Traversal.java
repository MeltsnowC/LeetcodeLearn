package algorithem.tree;

import algorithem.tree.nodes.MyTreeNode;
import sun.nio.cs.ext.MacTurkish;

import java.util.*;

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
     *
     * @param root
     */
    private void preOrderTraversalDigui(MyTreeNode root, List<Integer> result) {
        if (root == null) {//当前节点为空，直接返回，终止条件
            return;
        }
        result.add(root.val);
        preOrderTraversalDigui(root.left, result);
        preOrderTraversalDigui(root.right, result);
    }

    /**
     * 前序遍历调用方法
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversalByDigui(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderTraversalDigui(root, result);
        return result;
    }

    /**
     * 2.通过迭代方法实现前序遍历
     *
     * @param root
     */
    public List<Integer> preOrderTraversalDieDai(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<MyTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
//            按照中右左的顺序放入栈中，这样输出才能表示为中左右
//            先处理在入栈
            MyTreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return result;
    }

    /**
     * 3.中序遍历递归实现：左中右的顺序
     *
     * @param root
     * @param result
     */
    private void inOrderTraversalDiGui(MyTreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderTraversalDiGui(root.left, result);
        result.add(root.val);
        inOrderTraversalDiGui(root.right, result);
    }

    /**
     * 递归实现的中序遍历调用方法
     *
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversalByDigui(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderTraversalDiGui(root, result);
        return result;
    }

    /**
     * 4.中序遍历的迭代实现
     *
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversalDieDai(MyTreeNode root) {
//        中序遍历的迭代方法由于访问节点和处理节点顺序不同，因此需要使用指针遍历访问节点，然后使用栈处理节点
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<MyTreeNode> stack = new Stack<>();
        MyTreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {//还没有到达最左边
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 5.递归实现后序遍历：左右中
     *
     * @param root
     * @param result
     */
    private void postTraversalDigui(MyTreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postTraversalDigui(root.left, result);
        postTraversalDigui(root.right, result);
        result.add(root.val);
    }

    public List<Integer> postTraversalByDigui(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        postTraversalDigui(root, result);
        return result;
    }

    /**
     * 后序遍历的迭代实现，左右中=中右左的倒序=中左右交换左右迭代的顺序
     *
     * @param root
     * @return
     */
    public List<Integer> postTraversalByDieDai(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<MyTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            MyTreeNode pop = stack.pop();
            result.add(pop.val);
            if (pop.left != null) {//左先进，就会先出
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        Collections.reverse(result);
        return result;
    }


//    使用统一格式的前中后序迭代方法

    /**
     * 6.1 统一风格前序迭代
     *
     * @param root
     * @return
     */
    public List<Integer> preTraverUniformDieDai(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<MyTreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            MyTreeNode peek = stack.peek();
            if (peek != null) {//没到叶子
                stack.pop();//中节点先出栈，最后再进栈，构建统一格式，最后进栈，方便构建右左中->中左右
                //右边先进栈后出栈
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                if (peek.left != null) {
                    stack.push(peek.left);
                }
                stack.push(peek);
                stack.push(null);//多放一个null作为标记，预见null才会正式pop放入结果中。
            } else {
                stack.pop();//null出栈
                MyTreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }
        return result;
    }

    /**
     * 6.2 中序遍历统一格式
     *
     * @param root
     * @return
     */
    public List<Integer> inOrderTraverUniformDieDai(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<MyTreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            MyTreeNode peek = stack.peek();
            if (peek != null) {
                stack.pop();
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                stack.push(peek);
                stack.push(null);
                if (peek.left != null) {
                    stack.push(peek.left);
                }
            } else {
                stack.pop();
                MyTreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }
        return result;
    }

    /**
     * 6.3 后序遍历统一格式
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderTraverUniformDieDai(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<MyTreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            MyTreeNode peek = stack.peek();
            if (peek != null) {
                stack.pop();
                stack.push(peek);
                stack.push(null);
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                if (peek.left != null) {
                    stack.push(peek.left);
                }
            } else {
                stack.pop();
                MyTreeNode pop = stack.pop();
                result.add(pop.val);
            }
        }
        return result;
    }

    /**
     * 7.翻转左右子树
     * 使用前序遍历递归的方法
     *
     * @param root
     * @return
     */
    public MyTreeNode invertTree(MyTreeNode root) {
        //终止条件为节点为空的时候
        if (root == null) return root;
        //前序遍历，先翻转中间节点的子节点，再翻转左子树，右子树
        swapChildren(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 翻转节点
     *
     * @param root
     */
    private void swapChildren(MyTreeNode root) {
        MyTreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    /**
     * 7.2翻转左右子树，使用同一个迭代前序遍历
     *
     * @param root
     * @return
     */
    public MyTreeNode invertTreeByDieDai(MyTreeNode root) {
        if (root == null) return root;
        Stack<MyTreeNode> stack = new Stack<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            MyTreeNode peek = stack.peek();
            if (peek != null) {
                stack.pop();
                if (peek.right != null) stack.push(peek.right);
                if (peek.left != null) stack.push(peek.left);
                stack.push(peek);
                stack.push(null);
            } else {
                stack.pop();
                peek = stack.pop();
                swapChildren(peek);
            }

        }
        return root;
    }

    /**
     * 7.3递归的中序遍历方法实现子树翻转
     *
     * @param root
     * @return
     */
    public MyTreeNode invertTreeByDiguiInOrder(MyTreeNode root) {
        if (root == null) return root;
        invertTreeByDiguiInOrder(root.left);//左子树翻转
        swapChildren(root);//中节点翻转
        invertTreeByDiguiInOrder(root.left);//中节点翻转后，右子树变成左子树
        return root;

    }

    /**
     * 8. 判断是否是对称二叉树，
     * 递归方法实现
     *
     * @param root
     * @return
     */
    public boolean isSymmetric1(MyTreeNode root) {
        if (root == null) return true;
        boolean result = compare(root.left, root.right);
        return result;
    }

    /**
     * 是否对称二叉树，伪后序递归，
     * 左子树：左右中，右子树：右左中
     *
     * @param left
     * @param right
     * @return
     */
    private boolean compare(MyTreeNode left, MyTreeNode right) {
        if (left != null && right == null) {//左子树不为空，右子树空，不对称
            return false;
        } else if (left == null && right != null) {//左子树为空，右子树不为空，不对称
            return false;
        } else if (left == null && right == null) {//左右子树都为空，对称，
            return true;
        } else if (left.val != right.val) {//左右不相等，不对称。
            return false;
        }
        //左右子树外侧相同
        boolean outCompare = compare(left.left, right.right);
        boolean inCompare = compare(left.right, right.left);
        boolean result = outCompare && inCompare;
        return result;
    }

    /**
     * 9.对称二叉树，迭代方法
     * 使用队列，每次放入待对比的节点，然后取出头部的两个节点对比。放入取出节点的孩子节点
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(MyTreeNode root) {
        if (root == null) return true;
        Deque<MyTreeNode> deque = new LinkedList<>();
        deque.offerLast(root.left);
        deque.offerLast(root.right);
        while (!deque.isEmpty()) {
            MyTreeNode left = deque.pollFirst();
            MyTreeNode right = deque.pollFirst();
            if (left == null && right == null) continue;//先比较都为空的时候，否则可能会导致.val空指针异常
            if ((left == null && right != null) || (left != null && right == null) || (left.val != right.val)) {
                return false;
            }

            deque.offerLast(left.left);
            deque.offerLast(right.right);
            deque.offerLast(left.right);
            deque.offerLast(right.left);
        }
        return true;
    }

    /**
     * 10. 求二叉树的最大深度
     * 高度是节点到叶子节点的最长简单路径边的条数或者节点数
     * 深度是根节点到叶子节点的最长简单路径边的条数或者节点数
     * 后序遍历
     * 前序求深度，后序求高度
     *
     * @param root
     * @return
     */
    public int maxDepth(MyTreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);//左
        int rightDepth = maxDepth(root.right);//右
        return Math.max(leftDepth, rightDepth) + 1;//加1是算上根节点
    }

    /**
     * 11.求二叉树最大深度的精简版本
     *
     * @param root
     * @return
     */
    public int maxDepth2(MyTreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }

    /**
     * 求二叉树最大深度的前序遍历递归实现
     *
     * @param root
     * @return
     */
    int resultFormaxDepth3 = 0;//代表最大深度

    private void maxDepth3Digui(MyTreeNode root, int tempDepth) {
        if (root == null) return;
        resultFormaxDepth3 = resultFormaxDepth3 < tempDepth ? tempDepth : resultFormaxDepth3;
        maxDepth3Digui(root.left, tempDepth + 1);
        maxDepth3Digui(root.right, tempDepth + 1);
        return;
    }

    /**
     * 12.求二叉树最大深度的前序遍历递归实现
     *
     * @param root
     * @return
     */
    public int maxDepth3(MyTreeNode root) {
        maxDepth3Digui(root, 1);
        return resultFormaxDepth3;
    }

    /**
     * 13.最小深度我的递归求解
     * 后序遍历顺序
     *
     * @param root
     * @return
     */
    public int minDepth(MyTreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right != null) {//左子树为null,右子树不为null，则最小子树为右子树最小深度+1；
            return minDepth(root.right) + 1;
        }
        if (root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }


    int resultForminDepth2 = 0;

    private void minDepth2(MyTreeNode root, int depth) {

        if (root == null) return;//终止条件
        if (root.left == null && root.right == null) {
            resultForminDepth2 = Math.min(resultForminDepth2, depth);//中,判断是否是叶子节点
        }

        if (root.left != null) {//左
            minDepth2(root.left, depth + 1);
        }
        if (root.right != null) {//右
            minDepth2(root.right, depth + 1);
        }
        return;
    }
    /**
     * 14.前序遍历获取最小深度
     *
     * @param root
     * @return
     */
    public int minDepth2Result(MyTreeNode root){
        if (root==null) return 0;
        resultForminDepth2 = Integer.MAX_VALUE;
        minDepth2(root,1);
        return resultForminDepth2;
    }

    /**
     * 15.我自己的前序遍历
     * @param root
     * @return
     */
    public int minDepth4(MyTreeNode root) {
        if (root == null) return 0;
        if (root.left!=null && root.right!=null) return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        if (root.left == null && root.right != null) {//左子树为null,右子树不为null，则最小子树为右子树最小深度+1；
            return minDepth(root.right) + 1;
        }
        if (root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }
        return 1;//左右子树都为null的情况
    }

    /**
     * 16.计算完全二叉树的节点个数
     * 完全二叉树：除了最后一行没填满之外，其他行都填满，
     * 且最后一行节点都集中在最左边若干位置
     * 常用递归
     * 时间复杂度：O(n)
     * 空间复杂度：O(log n)，算上了递归系统栈占用的空间
     * @param root
     * @return
     */
    public int countTreeNods(MyTreeNode root){
        if (root==null) return 0;
//        int leftNum = countTreeNods(root.left);//左
//        int rightNum = countTreeNods(root.right);//右
//        int result = leftNum+rightNum+1;//中
        return countTreeNods(root.left)+countTreeNods(root.right)+1;
    }

    /**
     * 17.根据完全二叉树的定义计算节点个数
     * 在完全二叉树中，如果递归向左遍历的深度等于递归向右遍历的深度，那说明就是满二叉树。
     * @param root
     * @return
     */
    public int countTreeNodesByPerfect(MyTreeNode root){
        //递归终止条件：1.根据左右子树深度是否相同判断是否是一个满二叉树
        //             2.如果是满二叉树，则根据数学公式计算节点个数，否则继续递归
        if (root==null) return 0;
        MyTreeNode left = root.left;
        MyTreeNode right = root.right;
        int leftDepth=0,rightDepth=0;//统计左右子树深度，方便公式计算
        while (left!=null){//求左子树深度
            leftDepth++;
            left  = left.left;
        }
        while (right!=null){
            rightDepth++;
            right = right.right;
        }
        if (rightDepth==leftDepth){
            return (2 << rightDepth) - 1;
        }

        //单次递归
        int leftTreeNum = countTreeNodesByPerfect(root.left);
        int rightTreeNum = countTreeNodesByPerfect(root.right);
        int result = leftTreeNum+rightTreeNum+1;
        return  result;
    }

    /**
     * 18.判断是否平衡二叉树
     * 一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
     * 求高度用后序遍历，
     * 求深度用前序遍历
     * 递归方法求解
     * @param root
     * @return int 如果左右子树高度差>1，即表示不是平衡二叉树，返回-1，如果是二叉树，返回最大高度。
     */
    public int getHight(MyTreeNode root){
        //终止条件,如果节点为空，返回0
        if (root==null){
            return 0;
        }
        //单个递归中，左右中的顺序
        int leftDepth = getHight(root.left);
        if (leftDepth==-1) return -1;
        int rightDepth = getHight(root.right);
        if (rightDepth==-1) return -1;
        return Math.abs(leftDepth-rightDepth)>1?-1:1+Math.max(leftDepth,rightDepth);
    }

    /**
     * 使用迭代方法解决平衡树高度
     * 使用栈解决递归问题
     * 后序遍历左右中，放入栈的顺序为中右左。
     * @param root
     * @return
     */
    public boolean isBalence(MyTreeNode root){
        if (root==null){
            return true;
        }
        //利用栈执行后序遍历计算高度
        Stack<MyTreeNode> stack  = new Stack<>();
        stack.push(root);
        MyTreeNode pre = null;
        while (!stack.isEmpty()){
            MyTreeNode pop = stack.pop();
            if (Math.abs(getHightByDieDai(pop.left)-getHightByDieDai(pop.right))>1){
                return false;
            }
            if (pop.right!=null){
                stack.push(pop.right);
            }
            if (pop.left!=null){
                stack.push(pop.left);
            }
        }
        return true;
    }
    /**
     * 层序遍历获取节点的最大深度，也就是当前节点的高度
     * @param root
     * @return
     */
    public int getHightByDieDai(MyTreeNode root){
        int result = 0;
        if (root==null){
            return result;
        }
        Deque<MyTreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i=0;i<size;i++){
                MyTreeNode node = deque.pollFirst();
                if (node.left!=null){
                    deque.offerLast(node.left);
                }
                if (node.right!=null){
                    deque.offerLast(node.right);
                }
            }
            result++;
        }
        return result;
    }

    public int getHightByDieDai2(MyTreeNode root){
        int result = 0;
        if (root==null){
            return result;
        }
        int leftHeight = root.left != null ? root.left.val : 0;
        int rightHeight = root.right != null ? root.right.val : 0;
        result = Math.max(leftHeight, rightHeight) + 1;
        root.val = result;// 用TreeNode.val来保存当前结点的高度
        return result;
    }

}
