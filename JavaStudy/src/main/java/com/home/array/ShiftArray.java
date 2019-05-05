package com.home.array;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ShiftArray {



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        String[] nd = scanner.nextLine().split(" ");
//
//        int n = Integer.parseInt(nd[0]);
//
//        int d = Integer.parseInt(nd[1]);
//
//        int[] a = new int[n];
//
//        String[] aItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < n; i++) {
//            int aItem = Integer.parseInt(aItems[i]);
//            a[i] = aItem;
//        }
//
//        scanner.close();
        int n=5,d=4;
        int[] a={1,2,3,4,5};
        int[] b=new int[5];
        System.arraycopy(a,0,b,0,n);
        for(int i=0;i<n;i++){
            int target = i-d>=0?i-d:i-d+n;
            b[target]=a[i];
        }
        for(int i=0;i<n;i++){
            System.out.print(b[i]+" ");
        }
    }
}
