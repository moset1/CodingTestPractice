package P1189;


/*

백트래킹으로 경로를 검색하는 문제

go (int dist) {

    if( dist >= K) {
        cnt++;
        return;
    }

    for (int i=0; i<4; i++) {
        4방향
        범위체크 continue;
        방문체크 continue;
        visited[nr][nc] = true;
        go ( dist+1);
        visited[nr][nc] = false;

    }

}

 */

import java.util.Scanner;

public class P1189 {

    static int R,C,K;
    static char[][] map;
    static boolean[][] visited;
    static int[] MR = new int[] {-1,1, 0, 0};
    static int[] MC = new int[] {0,0, -1, 1};
    static int cnt = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        K = sc.nextInt();
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i=0; i<R; i++) {
           char[] temp = sc.next().toCharArray();
           for(int j=0; j<C; j++) {
               map[i][j] = temp[j];
               if (map[i][j] == 'T') {
                   visited[i][j] = true;
               }
           }

        }


        visited[R-1][0] = true;
        go(R-1, 0, 1);

        System.out.println(cnt);



    }

    static void go (int r, int c, int dist) {
        if( dist >= K) {
            if (r == 0 && c == C-1) {
                cnt++;
            }
            return;
        }

        for (int i=0; i<4; i++) {
            int nr = r + MR[i];
            int nc = c + MC[i];
            if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (visited[nr][nc]) continue;

            visited[nr][nc] = true;
            go (nr, nc, dist+1);
            visited[nr][nc] = false;

        }

    }



}
