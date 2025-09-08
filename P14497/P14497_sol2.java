package P14497;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 큐를 2개 이용해서 더 효율적인 풀이.

/*

0인 부분을 돌다가, 1인 부분을 만나면 1인 부분만 따로 저장하는 큐 만들기.
이 큐를 다시 시작큐로 만들기.


while (못찾으면)
cnt++
q1 = q0
q0 초기화

while(q1.isempty) {
    if
}
 */
public class P14497_sol2 {

    static int N, M;
    static int x1, y1, x2, y2;
    static char[][] map;
    static boolean[][] visited;
    static int[] MR = {-1,1, 0,0};
    static int[] MC = {0, 0, -1, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();


        map = new char[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];


        for (int i = 1; i <= N; i++) {
            char[] temp = sc.next().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j + 1] = temp[j];
            }
        }

        Queue<int[]> queue0 = new LinkedList<>();
        Queue<int[]> queue1 = new LinkedList<>();

        queue0.add(new int[] {x1, y1});
        visited[x1][y1] = true;
        int cnt = 0;
        boolean find = false;
        while(!find) {
            cnt++;


            while(!queue0.isEmpty()) {
                int[] temp = queue0.poll();
                int r = temp[0];
                int c = temp[1];

                for (int i=0; i<4; i++) {
                    int nr = r + MR[i];
                    int nc = c + MC[i];

                    if( nr <= 0 || nc <= 0 || nr > N || nc > M) continue;
                    if(visited[nr][nc]) continue;

                    if (map[nr][nc] == '1') {
                        queue1.add(new int[] {nr, nc});
                        visited[nr][nc] = true;

                    }
                    else if (map[nr][nc] == '0') {
                        queue0.add(new int[] {nr, nc});
                        visited[nr][nc] = true;

                    }
                    else {
                        find = true;
                        break;
                    }


                }
            }

            queue0 = queue1;
            queue1 = new LinkedList<>();
        }
        System.out.println(cnt);


    }


}
