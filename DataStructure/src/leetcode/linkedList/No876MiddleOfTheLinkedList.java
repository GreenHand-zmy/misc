package leetcode.linkedList;

import org.junit.Test;

public class No876MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        return middleNode1(head);
    }

    public ListNode middleNode1(ListNode head) {
        // 先遍历链表,求出链表长度
        int length = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            length++;
        }

        // 求出中间链表位置
        int middleIndex = length / 2 + 1;

        // 遍历到中间位置
        ListNode middleNode = head;
        for (int index = 0; index < middleIndex - 1; index++) {
            middleNode = middleNode.next;
        }
        return middleNode;
    }

    public ListNode middleNode2(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     */
    @Test
    public void testCase1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


        ListNode middleNode = middleNode(head);
    }

    /**
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     */
    @Test
    public void testCase2() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        ListNode middleNode = middleNode(head);
    }
}
