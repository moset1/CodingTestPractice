package P9935;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/*

StringBuilder 만들고

폭발 문자열 인덱스 처음에 찾고
앞에거- 폭발 문자열 갯수 정도는 sb에 더하고,
폭발 문자열 크기 이후랑 앞에거를 합치고,
거기부터 다시 찾기


 */
public class P9935 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        StringBuilder sb = new StringBuilder();

        char[] temp = str1.toCharArray();
        for (char c : temp) {
            sb.append(c);
            if (sb.length() < str2.length() ) continue;
            while( true) {
                if(sb.substring(Math.max(0,sb.length()  - str2.length()), sb.length()).equals(str2)){
                    sb.delete(Math.max(0,sb.length()  - str2.length()), sb.length());
                } else {
                    break;
                }
            }
        }

        if (sb.length() > 0) {
            System.out.println(sb);
        }
        else {
            System.out.println("FRULA");
        }







    }


}
