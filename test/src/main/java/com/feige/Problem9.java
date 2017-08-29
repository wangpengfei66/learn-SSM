package com.feige;

public class Problem9 {
    /**
     * 递归解法
     * @param n
     * @return
     */
    /*public int Fibonacci(int n) {
        if(n <= 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }*/
    public int Fibonacci(int n) {
        int []res = {0,1};
        if(n < 2){
            return res[n];
        }
        long result=0;
        long preOne=0;
        long preTwo=1;
        for (int i = 2;i<=n;i++) {
            result = preOne + preTwo;

            preOne = preTwo;
            preTwo = result;
        }
        return (int) result;
    }
    public int JumpFloorII(int n) {
        if(n <= 0) {
            return 0;
        }
        return (int)Math.pow(2,n-1);
    }

}
