package P3176;

import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class P3176 {

    static int N, K;
    static int A,B,C;

    static ArrayList<Vertex>[] graph;
    static int[] depth;
    static int[][] parent;


    static int[][] minDist, maxDist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        depth = new int[N+1];

        parent = new int[18][N+1];
        minDist = new int[18][N+1];
        maxDist = new int[18][N+1];

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            graph[A].add(new Vertex(A,B,C));
            graph[B].add(new Vertex(B,A,C));
        }

        // bfs로 depth 업데이트
        boolean[] visited = new boolean[N+1];
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(new Vertex(1, 1, 0));
        visited[1]  = true;
        while(!queue.isEmpty()) {
            Vertex v1 = queue.poll();


            for (int i=0; i<graph[v1.to].size(); i++) {
                    Vertex v2 = graph[v1.to].get(i);
                    if(visited[v2.to] == false) {
                        parent[0][v2.to] = v1.to;
                        minDist[0][v2.to] = v2.length;
                        maxDist[0][v2.to] = v2.length;
                        depth[v2.to] = depth[v1.to]+1;
                        queue.add(v2);
                        visited[v2.to] = true;
                    }
            }
        }

        for(int i=1; i<18; i++) {
            for(int j=1; j<N+1; j++) {
                if(parent[i-1][j] != 0) {
                    parent[i][j] = parent[i-1][parent[i-1][j]];
                    minDist[i][j] = Math.min(minDist[i-1][j], minDist[i-1][parent[i-1][j]]);
                    maxDist[i][j] = Math.max(maxDist[i-1][j], maxDist[i-1][parent[i-1][j]]);

                }

            }
        }





        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            String s = LCA(A, B);
            sb.append(s);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


    private static String LCA(int a, int b) {
        // b가 항상 depth가 큰 수이도록
        if(depth[a] > depth[b]) {
            int t = a;
            a = b;
            b = t;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // depth가 같도록 맞춰주기

        for(int k=17; k>=0; k--) {
            if(depth[b] - depth[a] >= (1 << k)) {
                min = Math.min(min, minDist[k][b]);
                max = Math.max(max, maxDist[k][b]);

                b = parent[k][b];
            }
        }


        // depth가 같은데 같은 숫자면 그 수 리턴
        if( a ==b ) {
            return min +" " + max;
        }


        // 다른 숫자면 lca 구하기
        for(int i=17; i>=0; i--) {
            if(parent[i][a] != parent[i][b]){
                min = Math.min(min, minDist[i][a]);
                min = Math.min(min, minDist[i][b]);
                max = Math.max(max, maxDist[i][a]);
                max = Math.max(max, maxDist[i][b]);

                a = parent[i][a];
                b = parent[i][b];
            }
        }

        min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
        max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));
        return min +" " + max;

    }

    static class Vertex{
        int from;
        int to;
        int length;

        public Vertex (int from, int to, int length){
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
