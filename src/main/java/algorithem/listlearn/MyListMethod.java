package algorithem.listlearn;

public class MyListMethod {


//    1.移除链表元素
    /**
     *   1.1添加头节点移除链表元素 time:o(n),空间：o(1)。
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementWithHead(ListNode head,int val) {
        if (head == null) {
            return head;
        }

//        因为删除可能涉及头节点，因此设置dummy节点，进行统一操作
            ListNode dummy = new ListNode(-1, head);

            ListNode pre = dummy;
            ListNode cur = head;

            while (cur != null) {
                if (cur.val == val) {
                    pre.next = cur.next;
                } else {
                    pre = cur;
                }
                cur = cur.next;
            }
            return dummy;
        }



    /**
     * 1.2不添加虚头节点，删除指定元素，time:o(n),空间:o(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementWithoutHead(ListNode head,int val){
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null){
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        while (cur!=null){
            if (cur.val==val && cur!=head){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 1.3 不添加pre节点，也不添加虚头节点，移除节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementWithoutHeadAndPre(ListNode head,int val){
        while (head!=null && head.val ==val){
            head = head.next;
        }
        ListNode cur = head;
        while (cur!=null){
            while (cur.next!=null && cur.next.val==val){
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }






}
