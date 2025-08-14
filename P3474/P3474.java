package P3474;

import java.util.Scanner;

public class P3474 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int i=0; i<T; i++) {

            long N = sc.nextInt();

            long five = 5;
            long two = 2;
            int cnt5 = 0;
            int cnt2 = 0;
            while(five <= N) {
                cnt5 += N/five;
                five *= 5;
            }

            while(two <= N) {
                cnt2 += two;
                two *= 2;
            }

            // 초기값, 종료 조건, 다음 연산이 일정하므로 for문으로 쓰면 더 짧다. 
            System.out.println(Math.min(cnt5, cnt2));
        }
    }
}
