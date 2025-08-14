package P17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P17472 {

    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[] MX = {-1, 1, 0, 0};
    static int[] MY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());

        map = new int[N][M];
        // map 입력
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬을 구분

        visited = new boolean[N][M];
        int island = 1;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if(map[i][j] != 0 && visited[i][j] == false) {
                    dfs(j, i, island);
                    island++;
                }
            }
        }
//
//        for(int i=0; i< N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }

        // 프림 알고리즘 풀이
        int from, to, value;
        PriorityQueue<Vertex> pq = new PriorityQueue<>((o1,o2) -> (o1.length - o2.length));
        ArrayList<Vertex>[] graph = new ArrayList[7];
        for(int i=0; i< 7; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i=0; i<N; i++) {
            from =0;
            to = 0;
            value = 0;

            for (int j=0; j<M; j++) {
                if(map[i][j] != 0 && from == 0 ) {
                    from = map[i][j];
                } else if (map[i][j] == 0 && from != 0) {
                    value +=1;
                } else if (map[i][j] != 0 && from != 0) {
                    to = map[i][j];
                    if (value >= 2) {
                        graph[from].add(new Vertex(from, to, value));
                        graph[to].add(new Vertex(to, from, value));
                    }
                    from = to;
                    to = 0;
                    value = 0;
                }
            }
        }


        for(int x=0; x<M; x++) {
            from = 0;
            to =0;
            value =0;
            for (int y=0; y<N; y++) {
                if (map[y][x] != 0 && from == 0 ){
                    from = map[y][x];
                } else if (map[y][x] == 0 && from !=0) {
                    value += 1;

                }else if (map[y][x] != 0 && from != 0){
                    to = map[y][x];
                    if (value >= 2) {

                        graph[from].add(new Vertex(from, to, value));
                        graph[to].add(new Vertex(to, from, value));

                    }
                    from = to;
                    to = 0;
                    value = 0;
                }
            }
        }


        pq.add(new Vertex(1, 1, 0));
        int sum = 0;
        int count = 0;
        boolean[] selected = new boolean[island];
        while(!pq.isEmpty()) {
            Vertex vertex = pq.poll();

            if( selected[vertex.to] == false) {
                selected[vertex.to] = true;
                sum += vertex.length;
                count++;

                for (int i=0; i< graph[vertex.to].size(); i++) {
                    Vertex vertex2 = graph[vertex.to].get(i);
                    if(selected[vertex2.to] == false) {
                        pq.add(graph[vertex.to].get(i));
                    }


                }
            }
            if(count == island-1) {
                break;
            }

        }
        if (count < island-1) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
        }


    }
    static void dfs(int x, int y, int island) {
        visited[y][x] = true;
        map[y][x] = island;
        for(int i=0; i<4; i++) {
            int mx = x + MX[i];
            int my = y + MY[i];

            if (my >= 0 && my < N && mx >= 0 && mx < M) {
                if (map[my][mx] ==1 ) {
                    if (visited[my][mx] == false) {
                        dfs(mx, my, island);
                    }

                }
            }
        }


    }

    static class Vertex {
        int from;
        int to;
        int length;

        public Vertex(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "from=" + from +
                    ", to=" + to +
                    ", length=" + length +
                    '}';
        }
    }
}
