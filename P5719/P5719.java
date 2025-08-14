package P5719;

import java.io.*;
import java.util.*;

public class P5719 {

    static ArrayList<Vertex>[] adj;
    static ArrayList<Integer>[] parent;

    static int N;
    static int M;
    static int S,D;
    static int U,V,P;

    static PriorityQueue<Vertex> pq;
    static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        while( N!=0 || M != 0) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            dist = new int[N];
            for (int i=0; i<N; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            pq = new PriorityQueue<>((o1,o2)-> (o1.length - o2.length));
            adj = new ArrayList[N];
            for(int i=0; i<N; i++) {
                adj[i] = new ArrayList<>();
            }
            parent = new ArrayList[N];
            for(int i=0; i<N; i++) {
                parent[i] = new ArrayList<>();
            }


            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());

                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());

                adj[U].add(new Vertex(V, P));

            }

            // 다익스트라로 최단거리 구하기

            dijkstra();
//            System.out.println(Arrays.toString(dist));


            Queue<Integer> queue = new LinkedList<>();
            ArrayList<Integer> adjS = new ArrayList<>();
            // 최단경로를 지우기
           // BFS로 해야 편할듯
            for(int i=0; i<parent[D].size(); i++) {
                queue.add(parent[D].get(i));
                if(parent[D].get(i) == S) {
                    adjS.add(D);
                }
            }

            while(!queue.isEmpty()){
                int to = queue.poll();
                //목적지인가?


                //연결된 곳을 순회
                Iterator<Vertex> it = adj[to].iterator();

                while(it.hasNext()) {
                    Vertex v = it.next();
                    if(to == S) {
                        if(adjS.contains(v.to)) {
                            it.remove();
                        }
                    }
                    else if (v.to == D || parent[v.to].contains(to)) {


                        it.remove();  // ✅ 안전하게 간선 삭제

                    }
                }

                for (int j=0; j<parent[to].size(); j++) {
                    queue.add(parent[to].get(j));
                    if(parent[to].get(j) == S) {
                        adjS.add(to);
                    }
                }



            }



            dist = new int[N];
            for (int i=0; i<N; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            dijkstra();
           // System.out.println(Arrays.toString(dist));
            if(dist[D] == Integer.MAX_VALUE ){
                bw.write(-1 + "\n");

            } else {
                bw.write(dist[D] + "\n");
            }



            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

        }
        bw.flush();
        bw.close();



    }

    static void dijkstra() {

        pq.add(new Vertex(S,0));
        dist[S] = 0;

        while(!pq.isEmpty()) {

            Vertex v1 = pq.poll();
            for( Vertex v2 : adj[v1.to]) {
                int distance = dist[v1.to] + v2.length;

                if (distance < dist[v2.to]) {
                    dist[v2.to] = distance;
                    parent[v2.to].clear();
                    parent[v2.to].add(v1.to);

                    pq.add(new Vertex(v2.to, distance));
                } else if (distance == dist[v2.to]) {
                    parent[v2.to].add(v1.to);
                }
            }
        }
    }


     static class Vertex {
         int to;
         int length;

         public Vertex(int to, int length) {
             this.to = to;
             this.length = length;
         }



         @Override
         public String toString() {
             return "Vertex{" +
                     "to=" + to +
                     ", length=" + length +
                     '}';
         }
     }
}
