package P4659;

import java.util.Scanner;

public class P4659 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = "";

        s = sc.next();

        while( !s.equals("end")) {

            boolean flag = false;
            boolean moumFlag = false;
            int mcnt = 0;
            int jcnt = 0;

            char previous = ' ';

            char[] nc = s.toCharArray();
            for( char c : nc) {
                if(isMoum(c)) {
                    mcnt++;
                    jcnt = 0;
                    moumFlag = true;

                } else {
                    jcnt++;
                    mcnt= 0;
                }

                if(mcnt >=3 || jcnt >= 3) {
                    flag = true;
                    break;
                }
                if(previous == c) {
                    if( c != 'e' && c != 'o') {
                        flag = true;
                        break;
                    }
                }

                previous = c;
            }

            if(moumFlag == false) {
                flag = true;
            }

            if(flag == true) {
                System.out.println("<" + s + "> is not acceptable.");
            } else {
                System.out.println( "<" + s + "> is acceptable." );
            }

            s = sc.next();
        }


    }

    public static boolean isMoum( char c) {
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ) {
            return true;
        }
        return false;
    }
}
