package algorithem.listlearn;

//链表节点定义
public class ListNode {
//    node value
    int val;
//    next node
    ListNode next;
//    constructor（no arg）
    public  ListNode(){};
    public ListNode(int val){
        this.val = val;
    }
    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        ListNode cur = this;
        while (cur!=null){
            stringBuilder.append(cur.val+"->");
            cur = cur.next;
        }
        return stringBuilder.toString()+"null";
    }
}
