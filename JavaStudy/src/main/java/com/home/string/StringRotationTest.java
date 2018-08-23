package com.home.string;

public class StringRotationTest {
    public static void main(String[] a){
        String origString = "waterbottle";
        String testSting  ="erbottlewat";
        System.out.println(isRotation( origString, testSting)==true?"rotated":"no");
    }
    public static boolean isRotation(String origString, String testSting){
        char[] s1=origString.toCharArray();
        char[] s2=testSting.toCharArray();
        char[] s3=new char[testSting.length()];
        char firstChar  = s1[0];
        int i=0;int j=0;
        boolean firstMatched =false;
        while(j<origString.length()){
            if(s2[i]==firstChar|| firstMatched){
                firstMatched=true;
                s3[j++]=s2[i];
            }
            i++;
            if(i==origString.length())
                i=0;
        }
        System.out.println(s3);
        return origString.equals(String.valueOf(s3));
    }
}
