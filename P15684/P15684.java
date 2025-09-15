package P15684;

import java.util.*;
/*
2차원 배열로 사다리 표현하기


go : 사다리 타기 실행하는 함수

go () {
    for (int i=0; i<N; i++) {
        int r = 0;
        int c = 0;
        while ( r <H) {
            if (map[r][c]) c++;
            else if(map[r][c-1]) c--;
            else {
                r++;
            }
        }

        if ( c != i) return false;
    }

    return true;
}

solve(int cnt) {


    if (cnt > 3 ) {
        return;
    }
    if (go()) {
        ret = cnt;
        return;
    }


    for(int i=0; i<H; i++) {
        for(int j=0; j<N; j++) {
            if(map[i][j]) continue;

            map[i][j] = true;
            solve(cnt+1);
            map[i][j] = false;

        }
    }

}


 */
public class P15684 {

    static boolean[][] map;
    static int N, M, H;
    static int ret = Integer.MAX_VALUE;
    static boolean find = false;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        H = sc.nextInt();
        map = new boolean[H+1][N+1];
        for (int i=0; i<M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            map[a][b] = true;
        }
        solve(1, 0);
        if (find) {
            System.out.println(ret);
        } else {
            System.out.println(-1);
        }

    }
    static boolean go () {
        for (int i=1; i<=N; i++) {

            int c = i;

            for (int j= 1; j<=H; j++) {
                if( map[j][c]) c++;
                else if (  map[j][c-1]) c--;
            }

            if ( c != i) return false;
        }

        return true;
    }


    static void solve(int row, int cnt) {

        if(go()) {
            ret = Math.min(ret, cnt);
            find = true;
            return;
        }
        if (cnt >= 3 || cnt >= ret) {
            return;
        }

        for(int i=row; i<=H; i++) {
            for(int j=1; j<N; j++) {
                if(map[i][j] ) continue;
                if( map[i][j-1]) continue;
                if(map[i][j+1]) continue;
                map[i][j] = true;
                solve(i,cnt+1);
                map[i][j] = false;

            }
        }

    }



}
