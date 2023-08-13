package algorithem.listlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDoubleLinkList {
    Logger logger = LoggerFactory.getLogger(MyLinkList.class);
    //size存储链表元素个数
    int size;
    //虚拟头节点和尾节点
    DoubleListNode dummy,tail;
    public MyDoubleLinkList(){
        size = 0;
        dummy = new DoubleListNode(0);
        tail = new DoubleListNode(0);
        dummy.next = tail;
        tail.pre = dummy;
    }

    public MyDoubleLinkList(int[] li){
        size = 0;
        dummy = new DoubleListNode(0);
        tail = new DoubleListNode(0);
        dummy.next = tail;
        tail.pre = dummy;
        if (li.length>0){
            for (int i=0;i<li.length;i++){
                addAtTail(li[i]);
            }
        }
    }

    public String toString(){
        StringBuilder sbpre = new StringBuilder();
        StringBuilder sbnext = new StringBuilder();
        DoubleListNode cur = dummy.next;
        DoubleListNode curtail = tail.pre;
        while (cur.next!=null){
            sbnext.append(cur.val+" --> ");
            sbpre.append(curtail.val+"-->");
            logger.info("cur-->{}",cur.val);
            cur = cur.next;
            curtail = curtail.pre;
        }
        return "next: "+sbnext.toString()+"null\n"+"pre: "+sbpre.toString()+"null";
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
        DoubleListNode cur = dummy;
//        判断index从哪边遍历更快
        if (index>size/2){
//            从tail开始
            cur = tail;
            for (int i=size-1;i>=index;i--){
                cur = cur.pre;
            }
        }else {
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        }
        return cur.val;
    }

    /**
     * 3.新节点添加在第一个元素之前，新节点作为第一个节点
     * @param val
     * @return
     */
    public DoubleListNode addAtHead(int val){
        return addAtIndex(0,val);
    }

    /**
     * 4.节点追加到末尾
     * @param val
     * @return
     */
    public DoubleListNode addAtTail(int val){
        return addAtIndex(size,val);
    }

    /**
     * 5.在第index节点之前添加节点，若index等于链表长度，则添加到末尾，若index大于链表长度，不会插入节点，
     * 如果index小于0，则在头部插入节点
     * @param index
     * @param val
     * @return
     */
    public DoubleListNode addAtIndex(int index,int val){
        if (index>size){
            return dummy;
        }
        if (index<0){
            return addAtIndex(0,val);
        }
        size++;
        DoubleListNode valNode = new DoubleListNode(val);

        //获取index节点的前驱节点
        DoubleListNode pre = dummy;
        for (int i =0;i<index;i++){
            pre = pre.next;
        }
        valNode.next = pre.next;
        pre.next.pre = valNode;
        pre.next = valNode;
        valNode.pre = pre;
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
        DoubleListNode pre = dummy;
        for (int i =0;i<index;i++){
            pre = pre.next;
        }
        pre.next.next.pre = pre;
        pre.next = pre.next.next;
        return true;
    }
}
