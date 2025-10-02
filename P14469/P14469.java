package P14469;


import java.util.*;

public class P14469 {


    public static void main(String[] args) {

        int n, t1, t2;

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        List<int[] > cowInfo = new ArrayList<>();

        for (int i=0; i<n; i++) {
            t1 = sc.nextInt();
            t2 = sc.nextInt();
            cowInfo.add(new int[] {t1, t2});
        }

        cowInfo.sort((o1,o2) -> o1[0] - o2[0]);

        long current = 0;


        for (int[] info : cowInfo) {
            current = Math.max(info[0], current);
            current += info[1];
        }

        System.out.println(current);
    }
}
