package P1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1915 {

    static int n, m;
    static int[][] map;
    static int[][] dp;
    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for(int j=0; j<m; j++) {
                int t = Integer.parseInt(s[j]);
                map[i][j] = t;
                if(t == 1) {
                    dp[i][j] = 1;
                    max = 1;
                }

            }
        }


        for(int y = 1; y<n; y++) {
            for(int x = 1; x<m; x++) {
                if(map[y][x] == 1) {
                    int t1 = dp[y-1][x-1];
                    int t2 = dp[y-1][x];
                    int t3 = dp[y][x-1];
                    if( t1>= 1 && t2 >=1 && t3 >=1) {
                        int min = Math.min(t1, Math.min(t2, t3));
                        dp[y][x] = min + 1;
                        max = Math.max(max, min+1);

                    }
                }

            }
        }

        System.out.println(max*max);
    }

}

/*

4 4
1000
0111
1111
1111
*/
