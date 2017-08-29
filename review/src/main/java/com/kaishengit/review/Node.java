package com.kaishengit.review;

public class Node {

	//链表在java中的表示形式
	Node next = null;
	int data;
	public Node(int data) {
		this.data = data;
	}
	//向链表中插入元素
	//链表头的引用
	Node head = null;
	public void addNode(int d) {
		Node newNode = new Node(d);
		if(head == null) {
			head = newNode;
			return;
		}
		Node tmp = head;
		while(tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = newNode;
	}
	
}
