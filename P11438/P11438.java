package P11438;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class P11438 {

    static int N, M;
    static int[][] parent;
    static int[] depth;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());

        parent = new int[18][N+1];
        depth = new int[N+1];
        graph = new ArrayList[N+1];

        for (int i=0; i< N+1; i++) {
            graph[i] = new ArrayList<>();
        }




        int a, b;
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);


        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);

        boolean[] visited = new boolean[N+1];

        visited[1] = true;
        while(!queue.isEmpty()) {

            int num = queue.poll();

            for (int i=0; i<graph[num].size(); i++) {
                int adj = graph[num].get(i);
                if( visited[adj] == false ) {
                    visited[adj] = true;
                    depth[adj] = depth[num] + 1;
                    parent[0][adj] = num;

                    queue.add(adj);
                }
            }
        }

        for(int i=1; i<18; i++) {
            for(int j=1; j<=N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
 /*       for(int i=0; i<18; i++) {
            System.out.println(Arrays.toString(parent[i]));
        }*/

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i=0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken() ) ;

            int ddiff = depth[a] - depth[b];
            // depth 맞추기
            int bigger, smaller;
            if (ddiff >= 0) {
                bigger = a;
                smaller = b;
            } else  {
                bigger = b;
                smaller = a;
            }

            while(depth[bigger] != depth[smaller]) {
                ddiff = depth[bigger] - depth[smaller];
                int k = 0;
                while(ddiff>= Math.pow(2, k) ) {
                    k++;
                }
                bigger = parent[k-1][bigger];



            }

            sb.append(LCA(bigger, smaller));
            sb.append("\n");


        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static int LCA(int a, int b) {

        if (a == b) {
            return a;
        }

        for( int k= 17; k>=0; k--) {
            if(parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b= parent[k][b];
            }
        }
        return parent[0][a];
    }
}
