package P1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1167 {

    static boolean[] visited;
    static ArrayList<Edge>[] A;
    static int Max;
    static int NodeMax;
    public static void main(String[] args) throws IOException {


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int V = Integer.parseInt(st.nextToken());

        visited = new boolean[V+1];
        A = new ArrayList[V+1];

        for (int i=1; i< V+1; i++) {
            A[i] = new ArrayList<Edge>();

        }
        for(int i=0; i<V; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            while(true) {
                int node = Integer.parseInt(st.nextToken());
                if (node==-1){
                    break;
                }

                int edge = Integer.parseInt(st.nextToken());
                A[n].add(new Edge(node, edge));
            }


        }
        Max = 0;

        DFS(1, 0);
        visited = new boolean[V+1];
        DFS(NodeMax, 0);

        System.out.println(Max);
    }

    public static void DFS(int node, int d) {
        if (d>Max) {
            Max = d;
            NodeMax = node;
        }
        visited[node] = true;
        for (Edge e : A[node]){
            if(!visited[e.Node]){

                DFS(e.Node, d+ e.d);
            }
        }
    }

}

class Edge {
    int Node;
    int d;

    public Edge(int Node, int d) {
        this.Node = Node;
        this.d = d;
    }
}
