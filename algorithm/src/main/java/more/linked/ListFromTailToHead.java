package more.linked;

import java.util.ArrayList;

/**
 * 输入一个链表，从尾到头打印链表每个节点的值
 * Created by ZMY on 2017/7/26.
 */
public class ListFromTailToHead {
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        ListFromTailToHead listFromTailToHead = new ListFromTailToHead();
        ArrayList<Integer> integers = listFromTailToHead.printListFromTailToHead(listNode);
        System.out.println(integers);
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}

