package P3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P3055 {

    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0,0, -1, 1};
    static int R, C;

    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;
    static boolean foundAnswer = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();

        Point b = null;

        for(int i=0; i<R; i++) {
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    b = new Point(i, j, 'S');
                } else if (map[i][j] == '*'){
                    queue.add(new Point(i, j, '*'));
                }
            }
        }



        queue.add(b);


        while(!queue.isEmpty()){

            // 1. 큐에서 꺼내요
            Point p = queue.poll();

            // 2. 목적지인가?
            if (p.type == 'D') {
                System.out.println(dp[p.y][p.x]);
                foundAnswer = true;
                break;
            }

            // 3. _연결된 곳을 순회
            for(int i=0; i<4; i++) {

                int ty = p.y + MY[i];
                int tx = p.x + MX[i];

                // 4. 갈 수 있는가?
                if( ty >= 0 && ty < R && tx>=0 && tx <C) {

                    // 고슴도치 또는 빈 공간인 경우
                    if(p.type == '.' || p.type== 'S') {
                        if (map[ty][tx] =='.' || map[ty][tx] == 'D'){

                            // '.'일 때는 중복을 피해야한다. dp 값이 0이 아니면 이미 거기로 갔으므로 제외
                            if((p.type == '.' &&  dp[ty][tx] == 0) || p.type=='S') {
                                dp[ty][tx] = dp[p.y][p.x] + 1;
                                queue.add(new Point(ty, tx, map[ty][tx]));
                            }

                        }
                    } else {
                        // 물인 경우 : ., S 로 이동 가능
                        if (map[ty][tx] =='.' || map[ty][tx] == 'S') {
                            map[ty][tx] = '*';
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }

            }



            // 4. __갈 수 있는가?

            // 5. __체크인

            // 6. __큐에 넣음
        }
        if (foundAnswer == false) {
            System.out.println("KAKTUS");
        }


    }


}

class Point {
    int y;
    int x;
    char type;

    public Point(int y, int x, char type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Point{" +
                "y=" + y +
                ", x=" + x +
                ", type=" + type +
                '}';
    }
}
