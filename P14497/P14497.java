package P14497;



/*
*에서 파동을 어떻게 구현할 수 있을까?
*
* 기울기? 맞지 않는다.
* 한 겹씩? 이것도 맞지 않는다.
* 더 이상 퍼져나가지 못하는 것을 visited로 구현
* => visited로 막혀서 더 이상 나갈 곳이 없으면 그 턴 끝내는걸로
*
*

* 상하좌우에서 각각 시작하기
*
* bfs가 좋을까 dfs가 좋을까?
* 매 턴마다 visited 초기화
* 일단 1을 만나면 visited=true로 바꾸고 로직을 끝내기
* 0을 만나면 4방향을 가기 (visited= true면 가지 않기)
* 간 후에는 visited = true 표시하기
* dfs가 로직 짜기 편하니까 dfs로 짜기
* => x. 턴수를 세야하니까 bfs로 만들어야함
*
*==> 이렇게 짤 경우 비효율적인 부분이 발생한다.
* 이전 턴에서 방문했던 부분을 퍼져나가는 걸 구현하기 위해 다시 방문해야하기 때문이다.
*
* 따라서 1인 지점을 저장해뒀다가 그 부분부터 다시 시작하도록 하는 로직이 더욱 효율적이다.
* 이러한 풀이가 바로 큐를 2개 사용하는 풀이이다.
*
*
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P14497 {

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


        map = new char[N+1][M+1];
        visited = new boolean[N+1][M+1];


        for (int i=1; i<=N; i++) {
            char[] temp = sc.next().toCharArray();
            for (int j=0; j<M; j++) {
                map[i][j+1] = temp[j];
            }
        }


        int cnt = 0;


        boolean find = false;

        while (!find) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {x1,y1});
            visited = new boolean[N+1][M+1];
            visited[x1][y1] = true;
            cnt++;

            while(!queue.isEmpty()){
                int[] temp = queue.poll();

                int r = temp[0];
                int c = temp[1];
                if (map[r][c] == '1')  {
                    visited[r][c] = true;
                    map[r][c] = '0';
                    continue;
                }
                if (map[r][c] == '#') {
                    find = true;
                    break;
                }

                for (int i=0; i<4; i++) {
                    int nr = r + MR[i];
                    int nc = c + MC[i];

                    if (nr <= 0 || nc <= 0 || nr >N || nc > M) continue;
                    if (visited[nr][nc]) continue;

                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;   // 방문체크를 안해서 30분 소요
                }
            }
//
//            System.out.println(cnt + "번째");
//            for(int i=0; i<=N; i++) {
//                for (int j=0; j<=M; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("----------");


        }

        System.out.println(cnt);

    }
}
