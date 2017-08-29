package com.feige;

public class QuickSort {
    public static void sort(int [] array,int low,int high) {
        int i,j;
        int index;
        if(low >= high) {
            return;
        }
        i = low;
        j = high;
        index = array[i];
        while (i<j) {
            while (i<j && array[j] > index) {
                j --;
            }
            if(i<j)
                array[i++] = array[j];
            while (i<j && array[i]<index)
                i++;
            if(i<j)
                array[j--] = array[i];
        }
        array[i] = index;
        sort(array,low,i-1);
        sort(array,i+1,high);
    }
    public static void quickSort(int array[]) {
        sort(array,0,array.length - 1);
    }

    public static void main(String[] args) {
        int i = 0;
        int [] a = {5,3,2,6,1,0};
    }
}
