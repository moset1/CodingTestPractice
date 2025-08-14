package P2363;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P2636 {

        static int r, c;
        static int[][] map;
        static boolean[][] visited;
        static int[][] edge;
        static Queue<int[]> queue;

        static int[] MX = {-1, 1, 0, 0};
        static int[] MY = {0, 0, -1, 1};

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);


            r = sc.nextInt();
            c = sc.nextInt();

            map= new int[r][c];
            visited = new boolean[r][c];
            edge = new int[r][c];


            queue = new LinkedList<>();


            for (int i=0; i<r; i++) {
                for (int j=0; j<c; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int hour = 0;
            int cheezeBefore = 0;
            while(isMapAllZero(map) == false) {
                hour++;

                int[] start = {0,0};

                BFS(start, queue, map, edge, visited);  // edge가 표시됨

                cheezeBefore = removeEdge(edge);


                edge = new int[r][c];
                visited = new boolean[r][c];

            }

            System.out.println(hour);
            System.out.println(cheezeBefore);


        }

        static boolean isMapAllZero(int[][] map) {
            boolean flag = true;

            for (int i=0; i<r; i++) {
                for(int j=0; j<c; j++) {
                    if (map[i][j] == 1) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) break;
            }

            return flag;
        }

        static void showMap(int[][] map) {

            for(int i=0; i<r; i++) {
                for (int j=0; j<c; j++) {
                    System.out.print(map[i][j]+ " ");
                }
                System.out.println();
            }
        }


        static int removeEdge( int[][] edge) {
            int count = 0;
            for( int i=0; i<r; i++) {
                for (int j=0; j<c; j++) {
                    if(edge[i][j] == 1) {
                        count++;
                        map[i][j] = 0;
                    }
                }
            }

            return count;
        }

        static void BFS(int[] start, Queue<int[]> queue, int[][] map, int[][] edge, boolean[][] visited) {

            queue.add(start);

            visited[start[0]][start[1]] = true;

            while(!queue.isEmpty()) {

                int[] point = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = point[0] + MX[i];
                    int nc = point[1] + MY[i];

                    if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                    if (visited[nr][nc] == true) continue;

                    if (map[nr][nc] == 0) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                    if (map[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        edge[nr][nc] = 1;
                    }
                }
            }

        }
}
