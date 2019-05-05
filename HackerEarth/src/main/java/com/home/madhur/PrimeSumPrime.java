package com.home.madhur;

import java.util.*;
import java.util.stream.Collectors;

class PrimeSumPrime {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        String[] mn = scanner.nextLine().split(" ");

        int start = Integer.parseInt(mn[0]);

        int end = Integer.parseInt(mn[1]);


        // Write your code here
        long starttime = System.currentTimeMillis();
        List<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++)
            numbers.add(i);
        // System.out.println("Initial list "+numbers);
        numbers = numbers.stream()
                .filter(x -> x % 2 != 0)
                .filter(x -> isPrime(x))
                .filter(x -> isPrimeSum(x))
                .collect(Collectors.toList());
        for (int i = 0; i < numbers.size(); i++)
            System.out.print(numbers.get(i) + " ");
        System.out.println("\nTime taken " + (System.currentTimeMillis() - starttime) + " milliSeconds");

    }

    private static boolean isPrime(int x) {
        boolean isPrime = true;
        if (x == 2) return isPrime;
        if (x % 2 == 0) return false;
//        if(x%3==0)return false;
        int checkUpto = (int) Math.round(Math.sqrt(x) + 1);
        for (int i = 3; i <= checkUpto; i = i + 2) {
            if (x % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    private static boolean isPrimeSum(int x) {
        int sum = 0;
        int temp = x;
        while (temp > 0) {
            sum = sum + (temp % 10);
            temp = temp / 10;
        }
        return isPrime(sum);
    }
}

