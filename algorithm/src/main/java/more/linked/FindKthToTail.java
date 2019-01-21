package more.linked;

/**
 * 输入一个链表，输出该链表中倒数第k个结点
 * Created by ZMY on 2017/7/26.
 */
public class FindKthToTail {
    public ListNode FindKthToTail(ListNode head, int k) {
        /*if (head == null) {
            return null;
        }
        int size = 0;
        for (ListNode current = head; current != null; current = current.next) {
            size++;
        }
        if (k > size) {
            return null
        }
        ListNode current = head;
        for (int i = 0; i < size - k; i++) {
            current = current.next;
        }
        return current;*/
        ListNode pre = head;
        ListNode last = head;

        if (k <= 0 || head == null) {
            return null;
        }
        for (int i = 1; i < k; i++) {
            if (pre.next != null) {
                pre = pre.next;
            } else {
                return null;
            }
        }
        while (pre.next != null) {
            pre = pre.next;
            last = last.next;
        }
        return last;
    }

    public static void main(String[] args) {
        PalindromeList palindromeList = new PalindromeList();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        FindKthToTail findKthToTail = new FindKthToTail();
        ListNode listNode1 = findKthToTail.FindKthToTail(listNode, 0);
        System.out.println(listNode1.val);

    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}


