package com.feige;

public class Problem10 {

    public int NumberOf1(int n) {
        //把一个整数减去1，再和原来的整数做与运算，会把该整数最右边一个1变成0。
        int count = 0;
        while(n != 0) {
            n = n&(n-1);
            count ++;
        }
        return count;
    }
}
