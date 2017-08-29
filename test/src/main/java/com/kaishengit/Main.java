package com.kaishengit;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int num = sc.nextInt();
        String[] array = str.split(" ");
        int n = array.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.valueOf(array[i]);
        }
        Arrays.sort(a);
        System.out.println(a[a.length - num]);
    }
}
