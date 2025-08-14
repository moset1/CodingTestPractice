package P2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2178 {

    static boolean[][] visited;
    static int[][] nums;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nums = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for (int i=1; i<= N; i++){
            st= new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j=1; j<=M; j++) {
                nums[i][j] = Integer.parseInt(line.substring(j-1,j));
            }
        }

        BFS(N,M);

    }

    public static void BFS(int N, int M) {
        Queue<Point> queue = new LinkedList<>();
        Point p = new Point(1,1,1);
        queue.add(p);
        visited[p.x][p.y] = true;
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            if(point.x==N && point.y==M) {
                System.out.println(point.d);
                return;
            }
            if (point.x+1<=N ){
                if(nums[point.x+1][point.y] == 1 && visited[point.x + 1][point.y]!=true){
                    queue.add(new Point(point.x+1,point.y,point.d+1));
                    visited[point.x+1][point.y] = true;

                }

            }
            if(point.y+1<=M && nums[point.x][point.y+1] == 1 && visited[point.x][point.y+1]!=true){
                queue.add(new Point(point.x,point.y+1,point.d+1));
                visited[point.x][point.y+1] = true;
            }
            if (point.x-1>=1 && nums[point.x-1][point.y]==1 && visited[point.x-1][point.y]!=true) {
                queue.add(new Point(point.x-1,point.y,point.d+1));
                visited[point.x-1][point.y] = true;
            }
            if (point.y-1>=1 && nums[point.x][point.y-1]==1 && visited[point.x][point.y-1]!=true) {
                queue.add(new Point(point.x,point.y-1,point.d+1));
                visited[point.x][point.y-1] = true;
            }


        }
    }


}

class Point {
    int x;
    int y;
    int d;

    public Point(int x, int y, int d) {
        this.x =x;
        this.y = y;
        this.d = d;
    }
}
