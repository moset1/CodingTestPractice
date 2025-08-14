package P1325;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P1325 {

    static int n,m;
    static ArrayList<LinkedList<Integer>> tree;

    static int a,b;
    static int[] count;
    static Queue<Integer> queue;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>(n+1);
        count = new int[n+1];
        for (int i=0; i<n+1; i++) {
            tree.add(new LinkedList<>());
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            tree.get(b).add(a);

        }

        int max = 0;
        for (int i=1; i< n+1; i++) {
           int cnt = bfs(i);
           count[i] =cnt;
           if(cnt > max) {
               max = cnt;
           }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (count[i] == max) {
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb);
        br.close();


    }

    static int bfs(int here) {

        visited = new boolean[n+1];
        queue = new ArrayDeque<>();

        queue.add(here);
        visited[here] = true;
        int cnt = 1;
        while(!queue.isEmpty()) {

            int cur = queue.poll();

            for( int there : tree.get(cur)) {
                if (visited[there]) continue;

                queue.add(there);
                visited[there] = true;
                cnt++;

            }
        }

        return cnt;
    }
}
