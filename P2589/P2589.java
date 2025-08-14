package P2589;

import java.util.*;

public class P2589 {

    static int n, m;
    static int[][] map;
    static int[] MX = {-1, 1, 0, 0};
    static int[] MY = {0, 0, -1, 1};
    static int max;

    static List<int[]> land;
    static List<List<int[]>> combiList;

    static boolean[][] visited;

    public static void main(String[] args) {


        Scanner sc  = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];

        land = new ArrayList<>();
        combiList = new ArrayList<>();
        visited = new boolean[n][m];

        for(int i=0; i<n; i++) {
            String[] str = sc.next().split("");

            for(int j=0; j< str.length; j++) {
                if(str[j].equals("W")) {
                    map[i][j] = 0;
                }

                if (str[j].equals("L")){
                    map[i][j] = 1;
                    land.add(new int[] {i,j});
                }
            }
        }

//        List<int[]> ret = new ArrayList<>();
//
//        combi(ret, 0, 2);
//

        max = 0;

        for (int[] one : land) {

            bfs(one);

        }

        System.out.println(max);








    }

//    static void combi(List<int[]> list, int start, int c) {
//
//        if( c==0 ) {
//            combiList.add(new ArrayList<>(list));
//            return;
//        }
//
//        for(int i= start; i<land.size(); i++) {
//            list.add(land.get(i));
//            combi(list, i+1, c-1);
//            list.remove(list.size() -1);
//        }
//    }

    static void bfs(int[] one) {

        int[] start = one;

        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[n][m];
        queue.add(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()) {

            int[] temp = queue.poll();
            max = Math.max(max, temp[2]);

            for (int i=0; i<4; i++) {
                int nr = temp[0] + MY[i];
                int nc = temp[1] + MX[i];

                if(nr < 0 || nc < 0 || nr >= n || nc >=m) continue;
                if(map[nr][nc] == 0) continue;
                if (visited[nr][nc] == true) continue;
                queue.add(new int[] {nr, nc, temp[2]+1});
                visited[nr][nc] = true;


            }

        }



    }
}
