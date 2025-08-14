package P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P2143 {


    static Map<Long, Long> sumBs;
    static long[][] dp;
    static long[] A, B;

    static long T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        A = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        B = new long[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        dp = new long[m][m];
        for(int i=0; i<m; i++){
            dp[i][i] = B[i];
        }


        sumBs = new HashMap<>();
        long sumB;
        long t;


        // 이차원 배열로 시작, 끝 인덱스
        // dp[i][j] = dp[i][j-1] + B[j]

        for (int i=1; i<m; i++) {
            for (int j=0; j<i; j++) {
                dp[j][i] = dp[j][i-1] + B[i];
            }
        }

       /* for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++){
                System.out.print(dp[i][j] + " ");

            };
            System.out.println("");
        }
*/
        for (int i=0; i<m; i++) {
            for (int j=i; j<m; j++) {
                sumB = dp[i][j];
                if(sumBs.containsKey(sumB)) {
                    t = sumBs.get(sumB);
                    sumBs.replace(sumB, t+1);
                } else {
                    sumBs.put(sumB, 1L);
                }
            }
        }

        long[][] dpA = new long[n][n];
        for (int i=0; i<n; i++) {
            dpA[i][i] = A[i];
        }
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                dpA[j][i] = dpA[j][i-1] + A[i];
            }
        }
        long count=0;
        long num;
        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                num = T - dpA[i][j];
                if(sumBs.containsKey(num)) {
                    count += sumBs.get(num);
                }
            }
        }

        System.out.println(count);

    }
}
