package P2870;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class P2870 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<m; i++) {
            String str = sc.next();

            char[] chars = str.toCharArray();
            String num = "";
            boolean flag = false;
            for(char c : chars) {
                if( c >= '0' && c <= '9'){
                    flag = true;
                    num += c;
                    if(num.charAt(0) == '0' && num.length()>=2) {
                        num =  num.substring(1);
                    }
                } else {
                    if(flag) {
                        arr.add(num);

                    }
                    num = "";
                    flag = false;
                }


            }
            if (flag) {
                arr.add(num);
            }

        }

        Collections.sort(arr, (o1, o2) -> {
            if(o1.length() == o2.length() ) {
                return o1.compareTo(o2);
            } else {
                return o1.length() - o2.length();
            }
        });

        for(String n : arr) {
            System.out.println(n);
        }


        
    }
}
