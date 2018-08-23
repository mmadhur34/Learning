package com.home.collections.stacks;


import org.junit.Test;

import java.util.LinkedList;

public class SortStack<E extends Comparable> {
    public LinkedList<E> sortStackWithOneStacks(LinkedList<E> input){
        LinkedList<E> stackTo =new LinkedList<>();
        while(!input.isEmpty()){
            E val = input.pop();
            if(stackTo.isEmpty())
                stackTo.push(val);
            else
            {
                if(stackTo.peek().compareTo(val)>=0)
                    stackTo.push(val);
                else{
                    while(stackTo.peek().compareTo(val)<0){
                        input.push(stackTo.pop());
                    }
                    stackTo.push(val);
                }
            }
        }
        return stackTo;
    }
    @Test
    public void testSort(){
        SortStack<Integer> sortStack = new SortStack<>();
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.push(2);
        numbers.push(4);;
        numbers.push(1);
        numbers.push(5);
        numbers.push(6);
        System.out.println("initial stack "+numbers);
        System.out.println("after sorting "+sortStack.sortStackWithOneStacks(numbers));

    }
}
