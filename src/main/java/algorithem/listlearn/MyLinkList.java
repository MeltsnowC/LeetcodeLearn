package algorithem.listlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLinkList {
    Logger logger = LoggerFactory.getLogger(MyLinkList.class);
    //size存储链表元素个数
    int size;
    //虚拟头节点
    ListNode dummy;
    public MyLinkList(){
        size = 0;
        dummy = new ListNode(0);
    }
    public MyLinkList(int[] li){
        size = 0;
        dummy = new ListNode(0);
        if (li.length>0){
            for (int i=0;i<li.length;i++){
                 addAtTail(li[i]);
            }
        }
    }


    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        ListNode cur = dummy.next;
        while (cur!=null){
            stringBuilder.append(cur.val+" --> ");
            logger.info("cur-->{}",cur.val);
            cur = cur.next;
        }
        return stringBuilder.toString()+"null";
    }


//    时间复杂度: 涉及 index 的相关操作为 O(index), 其余为 O(1)
//    空间复杂度: O(n)
    /**
     * 2.获取链表中第index个节点的值，如果索引无效，则返回-1。index从0开始。
     * @param index
     * @return
     */
    public int get(int index){
        if (index>=size || index<0){
            return -1;
        }
        ListNode cur = dummy;
        for (int i=0;i<=index;i++){
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * 3.新节点添加在第一个元素之前，新节点作为第一个节点
     * @param val
     * @return
     */
    public ListNode addAtHead(int val){
        return addAtIndex(0,val);
    }

    /**
     * 4.节点追加到末尾
     * @param val
     * @return
     */
    public ListNode addAtTail(int val){
        return addAtIndex(size,val);
    }

    /**
     * 5.在第index节点之前添加节点，若index等于链表长度，则添加到末尾，若index大于链表长度，不会插入节点，
     * 如果index小于0，则在头部插入节点
     * @param index
     * @param val
     * @return
     */
    public ListNode addAtIndex(int index,int val){
        if (index>size){
            return dummy;
        }
        if (index<0){
            return addAtIndex(0,val);
        }
        size++;
        ListNode valNode = new ListNode(val);

        //获取index节点的前驱节点
        ListNode pre = dummy;
        for (int i =0;i<index;i++){
            pre = pre.next;
        }
        valNode.next = pre.next;
        pre.next = valNode;
        return dummy;
    }

    /**
     * 6.如果索引index有效，则删除链表第index个节点
     * @param index
     * @return
     */
    public boolean deletAtIndex(int index){
        if (index>=size || index < 0 || dummy.next==null){
            logger.info("输入索引异常或者链表为空");
            return false;
        }
        size--;
        //找到索引前驱节点
        ListNode pre = dummy;
        for (int i =0;i<index;i++){
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return true;
    }

    /**
     * 7.使用双指针法。在原链表的基础上翻转链表
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param head
     * @return
     */
    public ListNode reversList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode tem = null;
        while (cur!=null){
            tem = cur.next;
//            翻转操作
            cur.next = pre;
//            更新指针
            pre = cur;
            cur = tem;
        }
        return pre;
    }

    /**
     * 8.使用递归方法，在原链表的基础上翻转链表
     * 时间复杂度: O(n), 要递归处理链表的每个节点
     * 空间复杂度: O(n), 递归调用了 n 层栈空间
     * @param head
     * @return
     */
    public ListNode reversListByRecursion(ListNode head){
        return reverseRecursion(null,head);
    }
    private ListNode reverseRecursion(ListNode pre,ListNode cur){
        if (cur==null){
            return pre;
        }
        ListNode tem = cur.next;
        cur.next = pre;
        // pre = cur;
        // cur = temp;
        return reverseRecursion(cur,tem);
    }

    /**
     * 9.使用递归方法，从后往前翻转链表
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * @param head
     * @return
     */
    public ListNode reverseTailToHead(ListNode head){
        //边界条件
        if (head==null) return  null;
        if (head.next==null) return head;

        //翻转第二个节点之后的链表，最后一个节点变为头节点返回。
        ListNode result = reverseTailToHead(head.next);
        //将head作为尾巴
        head.next.next = head;
        head.next = null;
        return result;

    }


    /**
     * 10.两两交换链表中的相邻节点， 递归方法
     * [1,2,3,4]->[2,1,4,3]
     * @param head
     * @return
     */
    public ListNode swapPairsByRecursion(ListNode head){
        if (head==null || head.next==null) return head;
        ListNode next = head.next;
        ListNode newNode = swapPairsByRecursion(next.next);
        next.next = head;
        head.next = newNode;
        return next;
    }

    /**
     * 11.指针方法交换链表中的相邻节点
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param head
     * @return
     */
    public ListNode swapPair(ListNode head){
//        设置虚拟头节点
        ListNode dummyHead = new ListNode(-1);

        dummyHead.next = head;
        ListNode cur = dummyHead;

        ListNode tempnext = null;
        ListNode temp = null;
        while (cur.next!=null && cur.next.next!=null){
//            保存未交换的节点
            temp = cur.next.next.next;

            tempnext = cur.next;

            cur.next = cur.next.next;
            cur.next.next = tempnext;
            tempnext.next = temp;

            cur = cur.next.next;
        }
        return  dummyHead.next;
    }

    /**
     * 12.删除链表倒数第N个节点，并返回链表的头节点。
     * 使用快慢指针，让fast移动n步，然后让fast和slow同时移动。
     * 在链表中，快指针应该先走n+1步，这样slow可以指向被删节点前驱节点
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param head
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head,int n){

        ListNode dummyHead = new ListNode(-1);
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        dummyHead.next = head;
        for (int i=0;i<n;i++){
            fast = fast.next;
        }
        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }

    /**
     * 13.找出并返回两个单链表相交的起始节点,要求链表保持原始结构，
     * 如果 listA 和 listB 没有交点，intersectVal 为 0
     * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
     * @param headA
     * @param headB
     * @return
     */
    public ListNode  getIntersectionNode(ListNode headA, ListNode headB){
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0,lenB = 0;
        while (curA!=null){//求链表A长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null){
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        if (lenB>lenA){
//            让curA为最长链表的头，lenA其长;
//            交换AB
            int templen = lenB;
            ListNode templi = curB;
            lenB = lenA;
            curB = curA;
            curA = templi;
            lenA = templen;
        }
        //长度差
        int gap = lenA-lenB;
        while (gap-->0){
            curA = curA.next;
        }
        while (curB!=null){
            if (curB==curA){
                return curB;
            }
        }
        return null;
    }

    /**
     *  14.给定一个链表，返回链表开始入环的第一个节点索引。如果链表无环，则返回 null。
     *  使用快慢指针可以解决，也可以使用集合。
     *  根据数学定义，x = (n-1)(y+z)+z,其中，x为头节点到环形入口节点个数，y为环形入口到fast,slow指针相遇的节点数，相遇节点到环形入口的节点数为z。
     *  当n为1时，x=z即，从头节点和相遇节点同时出发，再次相遇即为入口位置。
     * @param head
     * @return ListNode
     */
    public ListNode  detectCycle(ListNode head){

        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast==slow){//相遇位置，但不是入口
                ListNode index1 = fast;
                ListNode index2 = head;
                while (index2!=index1){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
