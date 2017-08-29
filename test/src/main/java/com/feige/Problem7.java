package com.feige;

import java.util.Stack;

public class Problem7 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() throws RuntimeException{
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()) {
            throw new RuntimeException("队列为空，不能删除");
        }
        return stack2.pop();
    }

}
