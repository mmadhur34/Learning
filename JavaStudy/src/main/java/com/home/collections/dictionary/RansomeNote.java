package com.home.collections.dictionary;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RansomeNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String,Integer> magazineWords = new HashMap<String,Integer>();

        for(int i=0;i<magazine.length;i++) {
            if (magazineWords.containsKey(magazine[i]))
                magazineWords.put(magazine[i], magazineWords.get(magazine[i]) + 1);
            else
                magazineWords.put(magazine[i], new Integer(1));
        }
        Integer count=0;
        boolean allFound=true;
        for(int i=0;i<note.length;i++){
            if(magazineWords.containsKey(note[i])) {
                count = magazineWords.get(note[i]);
                if (count > 0)
                    magazineWords.put(note[i], count - 1);
                else{
                    allFound=false;
                    break;
                }
            }else{
                allFound=false;
                break;
            }
        }
        System.out.println(allFound?"Yes":"No");

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        String[] mn = scanner.nextLine().split(" ");
//
//        int m = Integer.parseInt(mn[0]);
//
//        int n = Integer.parseInt(mn[1]);
//
//        String[] magazine = new String[m];
//
//        String[] magazineItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < m; i++) {
//            String magazineItem = magazineItems[i];
//            magazine[i] = magazineItem;
//        }
//
//        String[] note = new String[n];
//
//        String[] noteItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < n; i++) {
//            String noteItem = noteItems[i];
//            note[i] = noteItem;
//        }
        String[] magazine= "ive got a lovely bunch of coconuts".split(" ");
        String[] note="ive got coconuts".split(" ");

        checkMagazine(magazine, note);

        scanner.close();
    }
}

