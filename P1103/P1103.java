package P1103;

import java.awt.*;
import java.util.*;
import java.io.*;
public class P1103 {

    static int N, M;
    static String[][] map;
    static boolean[][] visited;
    static int[] MX = {-1, 1, 0, 0};
    static int[] MY = {0, 0, -1, 1};
    static int count = 1;
    static int max = 1;
    static boolean flag = false;
    static Queue<Point> queue;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N+1][M+1];
        dp = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        queue = new LinkedList<>();

        for (int y=0; y<N; y++) {
            st = new StringTokenizer(br.readLine());
            String[] s = st.nextToken().split("");
            for(int x=0; x< s.length; x++) {
                map[y][x] = s[x];


            }

        }

        queue.add(new Point(0,0));
        int count = 1;
        while(!queue.isEmpty()){
            Point p1 = queue.poll();

            // 목적지인가?
            if (visited[p1.y][p1.x] == true) {
                dp[p1.y][p1.x] = -1;
            }

            for(int i=0; i<4 ; i++) {
                int tx = p1.x + Integer.parseInt(map[p1.y][p1.x]) * MX[i];
                int ty = p1.y + Integer.parseInt(map[p1.y][p1.x]) * MY[i];

                if(ty<N && ty >=0 && tx<M && tx >=0 && !map[ty][tx].equals("H")){

                    visited[ty][tx] = true;
                    queue.add(new Point(tx, ty));
                }
            }


        }


        if(flag == true) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }
    static void dfs(int x, int y) {


        visited[y][x] = true;

        // 목적지
        if (flag == true) {
            return;
        }

        for(int i=0; i<4; i++) {
            int tx = x + Integer.parseInt(map[y][x]) * MX[i];
            int ty = y + Integer.parseInt(map[y][x]) * MY[i];

            if(ty<N && ty >=0 && tx<M && tx >=0 && !map[ty][tx].equals("H")){
                if(visited[ty][tx] == false) {
                    count++;
                    max = Math.max(max, count);
                    dfs(tx, ty);
                } else {
                    flag = true;
                    return;
                }
            }
        }


        visited[y][x] = false;
        count--;


    }

    static class Point{
        int x;
        int y;
        public Point(int x , int y) {
            this.x = x;
            this.y = y;
        }
    }



}
