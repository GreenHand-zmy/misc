package more.linked;

/**
 * 对于一个链表，请设计一个时间复杂度为O(n),额外空间复杂度为O(1)的算法，判断其是否为回文结构。
 * 给定一个链表的头指针A，请返回一个bool值，代表其是否为回文结构。保证链表长度小于等于900。
 * 测试样例：
 * 1->2->2->1
 * 返回：true
 * Created by ZMY on 2017/7/26.
 */
public class PalindromeList {
    public boolean chkPalindrome(ListNode A) {
        // 设置快慢指针
        ListNode fast = A;
        ListNode slow = A;
        // 找到mid结点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode nextMid = slow.next;
        ListNode pre = null;
        ListNode next = null;
        // 逆置
        while (nextMid != null) {
            next = nextMid.next;
            nextMid.next = pre;
            pre = nextMid;
            nextMid = next;
        }
        // 依次比较两链表
        while (A != null && pre != null) {
            if (A.val != pre.val) {
                return false;
            }
            A = A.next;
            pre = pre.next;
        }
        return true;
    }


    public static void main(String[] args) {
        PalindromeList palindromeList = new PalindromeList();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(2);
        listNode.next.next.next = new ListNode(1);
        boolean b = palindromeList.chkPalindrome(listNode);
        System.out.println(b);

    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}

