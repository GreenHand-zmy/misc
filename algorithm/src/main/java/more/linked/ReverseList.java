package more.linked;

/**
 * 输入一个链表，反转链表后，输出链表的所有元素。
 * Created by ZMY on 2017/7/27.
 */
public class ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = head;
        ListNode reserve = null;
        while (first != null) {
            ListNode second = first.next;
            // 分离第一个元素
            first.next = reserve;
            reserve = first;
            first = second;
        }
        return reserve;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}

