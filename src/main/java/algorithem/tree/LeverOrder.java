package algorithem.tree;

import algorithem.tree.nodes.MultiTreeNode;
import algorithem.tree.nodes.MyTreeNode;
import algorithem.tree.nodes.Perfect2Node;

import java.util.*;

/**
 * 二叉树层序遍历
 * 广度优先搜素
 * 通过队列实现
 */
public class LeverOrder {
    public List<List<Integer>> resList = new ArrayList<List<Integer>>();//保存结果

    /**
     * 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> leverOrder(MyTreeNode root){
//        List<List<Integer>> lists = leverByQueue(root);
//        List<List<Integer>> lists = leverByDiGui(root, 0, resList);
        List<List<Integer>> lists = leverByDiGui(root, 0);
        Collections.reverse(resList);

        return lists;


    }

    /**
     * 1.二叉树层序遍历迭代实现-使用队列
     * @param root
     * @return
     */
    private List<List<Integer>> leverByQueue(MyTreeNode root){
        if (root == null){
            return resList;
        }
        Queue<MyTreeNode> queue = new LinkedList<>();//创建中间队列
        queue.offer(root);//将节点放入队列中，开始遍历
        while (!queue.isEmpty()){//当队列为空时表示遍历完所有节点
            int size = queue.size();//记录当前队列的大小，用于循环处理节点，
            List<Integer> temp = new ArrayList<>();
            //开始每层的循环
            for (int i=0;i<size;i++){//这里不能直接使用queue.size，因为它的大小是不断变化的
                MyTreeNode poll = queue.poll();
                if (poll.left!=null){//子节点入队列
                    queue.offer(poll.left);
                }
                if (poll.right!=null){
                    queue.offer(poll.right);
                }
                temp.add(poll.val);//记录当前节点的值
            }
            //遍历完一层，保存起来
            resList.add(temp);
        }
        return resList;
    }

    /**
     * 2.递归方式实现层序遍历
     * @param root
     * @param depth
     * @return
     */
    private List<List<Integer>> leverByDiGui(MyTreeNode root,int depth){
//        记录每个层级的节点
        if (root == null){
            return resList;
        }
        depth++;
        if (resList.size()<depth){
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> temp = new ArrayList<>();
            resList.add(temp);
        }
        resList.get(depth-1).add(root.val);
        leverByDiGui(root.left,depth);
        leverByDiGui(root.right,depth);
        return resList;
    }


    /**
     * 3.层序遍历，采用外部输入resList的方式进行递归，
     * @param root
     * @param depth
     * @param resList
     * @return
     */
    private List<List<Integer>> leverByDiGui(MyTreeNode root,int depth,List<List<Integer>> resList){
//        记录每个层级的节点
        if (root == null){
            return resList;
        }
        depth++;
        if (resList.size()<depth){
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> temp = new ArrayList<>();
            resList.add(temp);
        }
        resList.get(depth-1).add(root.val);
        leverByDiGui(root.left,depth,resList);
        leverByDiGui(root.right,depth,resList);
        return resList;
    }

    /**
     * 4.返回从树的右测视角看到的节点，也就是每层最后一个数组成的数组
     * 只需要在层序遍历的时候，加入每层的最后一个几点即可
     * @param root
     * @return
     */
    public List<Integer> rightSideView(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null){
            return result;
        }
        Queue<MyTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                MyTreeNode poll = queue.poll();
                if (poll.left!=null){
                    queue.offer(poll.left);
                }
                if (poll.right!=null){
                    queue.offer(poll.right);
                }
                if (i==size-1){
                    result.add(poll.val);
                }
            }
        }
        return result;
    }

    /**
     * 5.求层平均值组成的数组
     * 只需要层序迭代，然后计算每层的均值即可
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(MyTreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root==null){
            return result;
        }
        Deque<MyTreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            double sum = 0.0;
            for (int i=0;i<size;i++){
                MyTreeNode node = deque.pollFirst();
                if (node.left!=null){
                    deque.offerLast(node.left);
                }
                if (node.right!=null){
                    deque.offerLast(node.right);
                }
                sum+=node.val;//求单层的和
            }
            result.add(sum/size);
        }
        return result;
    }

    /**
     * 6.N叉树的层序遍历
     * 就是按照二叉树的层序遍历来，只不过使用foreach判断children是否为空，不为空就加入到队列中。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(MultiTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null){
            return result;
        }
        Deque<MultiTreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()){//队列不为空，就说明还有节点
            int size = deque.size();
            List<Integer> temp = new ArrayList<>();//记录当前层的节点
            for (int i=0;i<size;i++){//当前层，队列中的节点
                MultiTreeNode poll = deque.pollFirst();//队列节点出列

                if (poll.children == null || poll.children.size() == 0) {
                    continue;
                }
                //把poll对应的children全都入列
                for (MultiTreeNode node:poll.children){
                    if (node!=null){
                        deque.offerLast(node);
                    }
                }
                temp.add(poll.val);//记录当前值
            }
            result.add(temp);
        }
        return result;
    }

    /**
     * 7.在每个树行中找最大值_515
     * 还是层序
     * @param root
     * @return
     */
    public List<Integer> largestValues(MyTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root==null){
            return result;
        }
        Deque<MyTreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            for (int i=0;i<size;i++){
                MyTreeNode node = deque.pollFirst();
                if (node.left!=null){
                    deque.offerLast(node.left);
                }
                if (node.right!=null){
                    deque.offerLast(node.right);
                }
                max = max>node.val?max:node.val;
            }
            result.add(max);
        }
        return result;
    }

    /**
     * 8.填充完美二叉树的后继节点,二叉树和这个一个意思
     * @param root
     * @return
     */
    public Perfect2Node connect(Perfect2Node root) {
        Deque<Perfect2Node> deque = new LinkedList<>();
        if (root!=null){
            deque.offerLast(root);
        }

        while (!deque.isEmpty()){
            int size = deque.size();
            Perfect2Node pre = deque.pollFirst();//记录前继节点,初始化为每层首节点。
            if (pre.left!=null){
                deque.offerLast(pre.left);
            }
            if (pre.right!=null){
                deque.offerLast(pre.right);
            }
            for (int i=0;i<size;i++){
                Perfect2Node node = deque.pollFirst();
                pre.next = node;//在这里应该先链接一下，否则会缺少第一个节点的next。
                pre = node;
                if (node.left!=null){
                    deque.offerLast(node.left);
                }
                if (node.right!=null){
                    deque.offerLast(node.right);
                }
            }
            pre.next = null;//本层最后一个节点指向Null;

        }
        return root;
    }


}
