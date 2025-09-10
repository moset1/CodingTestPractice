package P1987;


/*

백트래킹 문제같다.

dfs로

알파벳 alphabetVisited 설정
mapVisited
방문한 알파벳은 표시

dfs (r, c, cnt)
    방문 체크
    alphabetVisited = true
    mapVisited= true
    cnt++

    for (4 방향)
        범위체크
        alphabetVisited continue
        mapVisited continue

        dfs ( nr, nc, cnt+1)

    max = Math.max(max, cnt)
    alphabetVisited = false
    mapVisited = false
    cnt--
 */

import java.util.Scanner;

public class P1987 {

    static int R,C;
    static char[][] map;
    static boolean[][] mapVisited;
    static boolean[] alphabetVisited;
    static int MAX = 0;
    static int[] MR = {-1, 1, 0, 0};
    static int[] MC = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        mapVisited = new boolean[R][C];
        alphabetVisited = new boolean[30];

        for (int i=0; i<R; i++) {
            char[] temp = sc.next().toCharArray();

            for (int j=0; j<C; j++ ) {
                map[i][j] = temp[j];
            }
        }

        dfs(0, 0, 1);
        System.out.println(MAX);


    }

    static void dfs(int r, int c, int cnt) {

        MAX = Math.max(MAX, cnt);
        char alphabet = map[r][c];
        alphabetVisited[alphabet - 'A'] = true;



        for (int i=0; i<4; i++) {
            int nr = r + MR[i];
            int nc = c + MC[i];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;


            if (alphabetVisited[map[nr][nc] - 'A']) continue;

            dfs(nr, nc, cnt+1);
        }


        alphabetVisited[alphabet - 'A'] = false;



    }
}
