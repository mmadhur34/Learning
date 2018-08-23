package com.home.array;

public class DuplicateArrayElement {
    public static void main(String ar[]){
        int[] a = {1,1,1,2,3,4,4,5,6,6};
        System.out.println("\nremoved elements "+removeDuplicates(a));
    }
    public static int removeDuplicates(int[] A) {
        if (A.length==0) return 0;
        int j=0;int i=1;int len=0;
        for (; i<A.length; i++)
            if (A[i]!=A[j]) A[++j]=A[i];
        len=++j;
        if(j<i){
            while(j!=i)
                A[j++]=0;
        }
        System.out.println("Final array##");
        for(int a :A)
        System.out.print(a+",");
        return len;
    }
}
