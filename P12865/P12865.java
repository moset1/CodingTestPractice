package P12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P12865 {

    static int N,K;

    static int[][] dp;
    static ArrayList<Stuff> stuffs;
    static int[] weights;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+1][K+1];
        stuffs = new ArrayList<>();
        weights = new int[100001];
        int w, v;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            stuffs.add(new Stuff(w, v));
            weights[w] = v;
        }
        stuffs.sort((o1,o2) -> (o1.w - o2.w));

        Stuff bag;
        for (int i=1; i<=N; i++) {
            bag = stuffs.get(i-1);

            for(int j=0; j<=K; j++) {
                if(bag.w >j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-bag.w] + bag.v);
                }
            }
        }
//        System.out.println(Arrays.toString(dp));

        int result = 0;
        for (int i=1; i<=K; i++) {
            result = Math.max(result, dp[N][i]);
        }
        System.out.println(result);


    }

    static class Stuff {

        int w;
        int v;

        public Stuff(int w, int v) {
            this.w = w;
            this.v = v;
        }


    }
}
