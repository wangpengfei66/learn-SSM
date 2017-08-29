package com.feige;

import com.com.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

public class PrintListFromTailToHead {
    public static List<Integer> listNodes = new ArrayList<Integer>();
    public static List<Integer> printListFromTailToHead(ListNode listNode) {

        if(listNode != null) {
            listNodes.add(listNode.val);
            printListFromTailToHead(listNode.next);
        }
        return listNodes;
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);

        node1.next=node2;
        node2.next=node3;

        List<Integer> list = printListFromTailToHead(node1);
        for(int i = list.size() -1;i >=0;i--) {
            System.out.println(list.get(i));
        }
    }
}
