package P3197;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*

큐 2개로 풀기

1. 매일 한 번씩 녹아야한다.
큐1 : bfs 돌면서 한 번 녹이는 역할
큐2 : 녹은 지점들을 저장하는 역할 -> 그 다음날의 시작점


map을 돌면서
    . 이면 큐1에 넣기

큐1 4방향 체크 하면서
    x면 큐2에 넣기
    x인 부분은 .으로 바꾸기


큐1 = 큐2
큐2 초기화

day++

백조 만나는지 확인하는 함수
dfs로 찾기

goVisited 초기화
go (r, c)  {
    if (map[r][c] =='L')
        find = true;
        return;

    for (int i=0; i<4; i++) {

        nr, nc

        범위체크
        visited 체크
        map이 x면 지나가기
        find == true면 return
        아니면 go(nr, nc)

 */
public class P3197 {

    static int R, C;
    static char[][] map;
    static boolean[][] goVisited;
    static Queue<int[]> queue1;
    static Queue<int[]> queue2;
    static int day = 0;
    static int[] MR = {-1,1, 0, 0};
    static int[] MC = {0, 0, -1, 1};
    static boolean find = false;
    static Queue<int[]> swan1;
    static Queue<int[]> swan2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.valueOf(st.nextToken());
        C = Integer.valueOf(st.nextToken());


        map = new char[R][C];
        goVisited = new boolean[R][C];

        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        swan1 = new LinkedList<>();
        swan2 = new LinkedList<>();

        int[] Lpos = new int[2];

        for (int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            char[] temp = st.nextToken().toCharArray();

            for (int j=0; j<C; j++) {
                map[i][j] = temp[j];
                if (temp[j] == '.' || temp[j] == 'L') {
                    queue1.add(new int[] {i, j});
                }
                if (temp[j] == 'L') {
                    Lpos = new int[] {i, j};
                }
            }
        }

        swan1.add(Lpos);
        goVisited[Lpos[0]][Lpos[1]] = true;


        while (!find) {
            //showMap();
            swanMove();
            if (find) break;
            dayOff();
        }

        System.out.println(day);




    }

    static void showMap() {
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    static void swanMove() {

        while(!swan1.isEmpty()) {

            int[] temp = swan1.poll();

            int r = temp[0];
            int c = temp[1];
            for (int i=0; i<4; i++) {
                int nr = r + MR[i];
                int nc = c + MC[i];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (goVisited[nr][nc]) continue;
                if (map[nr][nc] == '.' ) {
                    swan1.add(new int[] {nr, nc});
                    goVisited[nr][nc] = true;
                } else if (map[nr][nc] == 'X') {
                    swan2.add(new int[] {nr, nc});
                    goVisited[nr][nc] = true;
                } else {
                    find = true;
                    return;
                }
            }
        }

        swan1 = swan2;
        swan2 = new LinkedList<>();
    }

    static void dayOff() {
        day++;

        while(!queue1.isEmpty()) {
            int[] temp = queue1.poll();
            int r = temp[0];
            int c = temp[1];
            for (int i=0; i<4; i++) {
                int nr = r + MR[i];
                int nc = c + MC[i];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (map[nr][nc] == 'X') {
                    queue2.add(new int[] {nr, nc});
                    map[nr][nc] = '.';
                }
            }
        }

        queue1 = queue2;
        queue2 = new LinkedList<>();

    }




}
