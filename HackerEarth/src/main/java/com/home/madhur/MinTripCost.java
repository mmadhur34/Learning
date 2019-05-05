package com.home.madhur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MinTripCost {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] a){
        //firstLine
        int testCases=Integer.parseInt(scanner.nextLine());
        int checkPointCount=Integer.parseInt(scanner.nextLine());
        ArrayList<Integer> checkPoints =stringToNumberList(scanner.nextLine());
        ArrayList<Integer> distancePoints =stringToNumberList(scanner.nextLine());



    }

    private static ArrayList<Integer> stringToNumberList(String line){
        ArrayList<Integer> list =new ArrayList<>();
        Arrays.stream(line.split(" ")).forEach(x->{
            list.add(Integer.parseInt(x));
        });
        return list;
    }
}
