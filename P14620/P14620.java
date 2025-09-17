package P14620;


/*

map :  가격 정보

빽트래킹

back( int start, int cnt, int sum) {


    if (cnt >= 3  ) {
        ret = Math.min(ret, sum)
        return;
    }
    if (cnt > 3 || ret < sum) {

        return;
    }

    for (int i = start; i<N-1; i++) {

        for (int j=1; j<N-1; j++) {
            if(map[i][j]) {
                방문 체크
                back(start, cnt+1, sum + 4방향 값);
                방문 체크 지우기

            }

        }
    }


}


 */

import java.util.*;

public class P14620 {

    static int N;
    static int[][] map;
    static int ret = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[] MR = new int[] {0,0, -1, 1};
    static int[] MC = new int[] {-1,1, 0, 0};


    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        back(1,0,0);

        System.out.println(ret);



    }

    // startR도 없어도 된다.
    static void back(int startR, int cnt, int sum) {

        if (cnt >= 3  ) {

            if ( ret > sum) {
                ret = Math.min(ret, sum);
//                for(int i=0;i<N; i++) {
//                    for (int j=0; j<N; j++) {
//                        System.out.print(visited[i][j] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("----------");
            }

            return;
        }
        if (ret < sum) {
            return;
        }

        for (int i = startR; i<N-1; i++) {

            for (int j=1; j<N-1; j++) {


                if (!checkAround(i,j)) continue;

                // 방문 체크
                reverseCheck(i,j);

                back(i, cnt+1, sum + mapSum(i,j));
                //방문 체크 지우기
                reverseCheck(i,j);


            }
        }

    }
    static void reverseCheck(int r, int c) {

        visited[r][c] = !visited[r][c];

        for (int i=0; i<4; i++) {
            int nr = r + MR[i];
            int nc = c + MC[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            visited[nr][nc] = !visited[nr][nc];

        }


    }

    static boolean checkAround(int r, int c) {
        if(visited[r][c]) return false;
       for (int i=0; i<4; i++) {
           int nr = r + MR[i];
           int nc = c + MC[i];
           if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

           if (visited[nr][nc]) return false;

       }

        return true;

    }

    static int mapSum(int r, int c) {

        int sum = map[r][c];
        for(int i=0; i<4; i++) {
            int nr = r + MR[i];
            int nc = c + MC[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            sum += map[nr][nc];
        }
        return sum;

    }



}
