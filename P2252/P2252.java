package P2252;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P2252 {

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static LinkedList<Integer> queue;
    static int[] indegree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        indegree = new int[N+1];
        visited = new boolean[N+1];
        graph = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        int A, B;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            indegree[B] += 1;
        }


        for (int i=1; i<=N; i++) {
            if(indegree[i] == 0 && visited[i] == false) {
                queue.add(i);
                visited[i] = true;
            }
        }
        while(!queue.isEmpty()) {



            int num = queue.poll();


            for(int i=0; i<graph.get(num).size(); i++) {
                int val = graph.get(num).get(i);
                indegree[val] -= 1;
                if (indegree[val] == 0 && visited[val] == false) {
                    queue.add(val);
                    // queue에 넣은 걸 방문 표시 해야 중복되지 않는다.
                    visited[val] = true;
                }
            }

            bw.write(num + " ");


        }

        bw.flush();
        bw.close();

    }
}
