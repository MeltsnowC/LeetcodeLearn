package algorithem.listlearn;

public class DoubleListNode {
    int val;
    DoubleListNode pre;
    DoubleListNode next;
    DoubleListNode(int val){
        this.val = val;
    }
    DoubleListNode(int val,DoubleListNode pre,DoubleListNode next){
        this.val = val;
        this.pre = pre;
        this.next = next;
    }
}
