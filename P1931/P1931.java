package P1931;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*

시작하는 시간 기준 오름차순 =>

끝나는 시간 기준 오름차순

끝나는 시간 - 시작하는 시간 오름차순?

현재 시간 기준으로 최대 갯수를 구하는게 나으려나?


백트래킹?



 */
public class P1931 {

    static  int n, t1, t2;
    static List<int[] > meetingInfo;

    public static void main(String[] args) {



        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        meetingInfo = new ArrayList<>();

        for (int i=0; i<n; i++) {
            t1 = sc.nextInt();
            t2 = sc.nextInt();
            meetingInfo.add(new int[] {t1, t2});
        }

        meetingInfo.sort((o1,o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        long current = 0;
        int cnt = 0;
        for (int[] info : meetingInfo) {

            if (info[0] < current) continue;
            current = info[1];
            cnt++;
        }

        System.out.println(cnt);
    }

}
