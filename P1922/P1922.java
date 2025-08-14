package P1922;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1922 {

    static int N, M;
    static PriorityQueue<Vertex> pq;

    static ArrayList<Vertex>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[N+1];
        pq = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
        int a,b,c;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Vertex(b, c));

            graph[b].add(new Vertex(a,c ));



        }

        long sum = 0;
        pq.add(new Vertex(1, 0));



        while ( !pq.isEmpty() ) {

            Vertex v = pq.poll();
            if(visited[v.to] == false) {
                sum += v.value;
                visited[v.to] = true;
                for(int i=0; i< graph[v.to].size(); i++) {
                    pq.add(graph[v.to].get(i));

                }
            }





        }
        System.out.println(sum);
    }

    static class Vertex {
        int to;
        int value;

        public Vertex(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "to=" + to +
                    ", value=" + value +
                    '}';
        }
    }
}
