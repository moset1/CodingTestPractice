package P1644;

import java.util.*;

/*

소수 구하기

에라토스테네스의 체


 */
public class P1644 {

    static int n;
    static boolean[] isPrime;
    static List<Integer> primeNumber;
    static int low, high;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n= sc.nextInt();

        isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        primeNumber = new ArrayList<>();


        isPrime[0] = false;
        isPrime[1] = false;

        for(int i=2; i<=n; i++) {
            if(!isPrime[i]) continue;

            primeNumber.add(i);
            for(long j= (long) i*i; j<=n; j+=i) {
                isPrime[(int) j] = false;
            }
        }

        int cnt = 0;
        int sum = 0;
        low = 0;

        for (int high = 0; high < primeNumber.size(); high++) {

            sum += primeNumber.get(high);

            while(sum > n) {
                sum -= primeNumber.get(low);
                low++;
            }

            if (sum == n) {
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
