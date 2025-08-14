package P1260;

import java.util.*;

public class P1260 {

    static boolean[] visited;
    static ArrayList<Integer>[] A;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int Start = scan.nextInt();
        A = new ArrayList[N+1];
        for (int i=1; i<=N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        for (int i= 0; i< M; i++) {
            int S =scan.nextInt();
            int E = scan.nextInt();
            A[S].add(E);
            A[E].add(S);
        }

        for ( int i=1; i<=N; i++) {
            Collections.sort(A[i]);
        }
        visited = new boolean[N+1];
        DFS(Start);
        System.out.println();
        visited = new boolean[N+1];
        BFS(Start);
        System.out.println();
    }

    public static void DFS(int Node) {
        System.out.print(Node+" ");
        visited[Node] = true;
        for (int i : A[Node]) {
            if (!visited[i]){
                DFS(i);
            }
        }
    }

    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);

        visited[Node] = true;
        while(!queue.isEmpty()) {
            for(int i : A[queue.peek()]) {
                if(!visited[i]){
                    queue.add(i);
                    visited[i] = true;
                }
            }
            int num = queue.poll();
            System.out.print(num + " ");
        }
    }
}
