/*
package P3830;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P3830 {

    static int n, m;
    static int a, b;

    static ArrayList<Node> nodes;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n==0 && m == 0) {
                break;
            }

            nodes = new ArrayList<>(n+1);

            for (int i=0; i<=n; i++) {
                nodes.add(new Node(i, 0));
            }

            String op;
            int a, b, c;
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                op = st.nextToken();

                if (op.equals("!")){
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());

                    union(a, b, c);

                } else if(op.equals("?")) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());


                    int aRoot = find(a);
                    int bRoot = find(b);
                    if (aRoot != bRoot) {
                        System.out.println("UNKNOWN");
                    } else {
                        long aDiff = dfs(a, 0, aRoot);
                        long bDiff = dfs(b, 0, bRoot);

                        System.out.println(bDiff - aDiff);
                    }
                }
            }
        }





    }

    static long dfs(int node, long diff, int root) {
        if( node == root) {
            return diff;
        } else {
            return dfs(nodes.get(node).parent, nodes.get(node).diff + diff, root);
        }
    }

    static int find(int n) {
        Node node = nodes.get(n);
        if(node.parent == n) {
            return n;
        } else {

            return find(node.parent);

        }
    }

    static void union(int a, int b, long w) {
        Node aRoot = nodes.get(find(a));
        Node bRoot = nodes.get(find(b));
        if(aRoot.parent != bRoot.parent){
            aRoot.parent = nodes.get(b).parent;
            aRoot.diff = nodes.get(a).diff - (nodes.get(b).diff + w);
        }


    }

    static class Node {
        int parent;
        long diff;

        public Node(int parent, long diff) {
            this.parent = parent;
            this.diff = diff;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent +
                    ", diff=" + diff +
                    '}';
        }
    }
}
*/
package P3830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P3830 {

    static int n, m;
    static ArrayList<Node> nodes;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            nodes = new ArrayList<>(n + 1);
            for (int i = 0; i <= n; i++) {
                nodes.add(new Node(i, 0));
            }

            String op;
            int a, b, c;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                op = st.nextToken();

                if (op.equals("!")) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());

                    union(a, b, c);
                } else if (op.equals("?")) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());

                    if (find(a) != find(b)) {
                        System.out.println("UNKNOWN");
                    } else {
                        System.out.println(nodes.get(a).diff - nodes.get(b).diff);
                    }
                }
            }
        }
    }

    static int find(int x) {
        if (nodes.get(x).parent == x) {
            return x;
        } else {
            int parent = find(nodes.get(x).parent);
            nodes.get(x).diff += nodes.get(nodes.get(x).parent).diff; // diff 값 경로 압축
            return nodes.get(x).parent = parent;
        }
    }

    //경로압축 사용시 union을 호출할때 자동으로 경로 압축이 이루어짐.
    static void union(int a, int b, long w) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            nodes.get(rootA).parent = rootB;
            nodes.get(rootA).diff = nodes.get(b).diff + w - nodes.get(a).diff;
        }
    }

    static class Node {
        int parent;
        long diff;

        public Node(int parent, long diff) {
            this.parent = parent;
            this.diff = diff;
        }
    }
}