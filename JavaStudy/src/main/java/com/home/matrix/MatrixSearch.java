package com.home.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 *
 * A :
 * [
 *   [3]
 *   [29]
 *   [36]
 *   [63]
 *   [67]
 *   [72]
 *   [74]
 *   [78]
 *   [85]
 * ]
 * B : 41
 * Time limit exceeded
 */
public class MatrixSearch {
    public static void main(String args[]){
        ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>();
        numbers.add(new ArrayList<Integer>(Arrays.asList(1,3,5,7)));
        numbers.add(new ArrayList<Integer>(Arrays.asList(10,11,16,20)));
        numbers.add(new ArrayList<Integer>(Arrays.asList(23,30,34,50)));
        System.out.println("Number found 11: "+searchNumber(numbers,11));
        System.out.println("Number found 12: "+searchNumber(numbers,12));
        System.out.println("Number found 7: "+searchNumber(numbers,7));
        System.out.println("Number found 33: "+searchNumber(numbers,33));
        System.out.println("Number found 34: "+searchNumber(numbers,34));

    }
    public static int searchNumber1(ArrayList<ArrayList<Integer>> numbers,int b ){
        int startRow = 0; int endRow = numbers.size()-1;
        int currentRowIndex = (startRow+endRow)/2;
        while(currentRowIndex>=0){
            List<Integer> currRow = numbers.get(currentRowIndex);
            int rowMin = currRow.get(0);
            int rowMax = currRow.get(currRow.size()-1);
            if(b>rowMax){
                startRow = currentRowIndex+1;
                currentRowIndex = (startRow+endRow)/2;
            }else if(b<rowMin){
                endRow=currentRowIndex-1;
                currentRowIndex=(startRow+endRow)/2;
            }else{
                //number is in current row
                return Arrays.binarySearch(currRow.toArray(),b) >= 0?1:0;
            }

        }



        return 0;
    }
    public static int searchNumber(ArrayList<ArrayList<Integer>> numbers,int b){
        ArrayList<Integer> newList= new ArrayList<>();
        for(ArrayList<Integer> i:numbers)
            newList.addAll(i);
        return Collections.binarySearch(newList,b)>=0?1:0;
    }
}
