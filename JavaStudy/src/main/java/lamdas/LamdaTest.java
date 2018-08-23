package lamdas;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by madhur on 9/2/2017.
 */
public class LamdaTest {
    public static void main(String[] args){
        List<String> list = Arrays.asList("Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday");
        sorting(list);


        System.out.println("Working days" + getWorkingDays(list));


    }

    private static List<String> getWorkingDays(List<String> list) {
//        List<String> workdays = new ArrayList<>();
//        for (String field : list) {
//            if (meetsCriteria(field)) {
//                workdays.add(field);
//            }
//        }
//        return workdays;
        return list.stream()
                .filter(s1->!(Arrays.asList("Sunday","Saturday").contains(s1)))
                .collect(Collectors.toList());
    }

    private static boolean meetsCriteria(String field) {
        if(field.equals("Sunday")||field.equals("Saturday"))
            return false;
        else return true;
    }

    private static void sorting(List<String> list) {
        //        list.sort(new Comparator<String>() {
//            public int compare (String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//    } );

        list.sort((o1,o2)-> o1.length()-o2.length());
        System.out.println("length "+list);
        list.sort(Comparator.comparingInt(String::length));
        System.out.println("length "+list);
        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println("reverse "+list);
        list.sort(Comparator.comparing(String::toString));
        System.out.println("default "+ list);
        Collections.sort(list);
        System.out.println("default "+ list);
    }


}
