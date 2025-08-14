package P1717;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1717 {

    static int n, m;
    static int a, b;

    static ArrayList<Node> nodes;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>(n+1);

        for (int i=0; i<=n; i++) {
            nodes.add(new Node(i));
        }

        int t;
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if ( t == 0) {
                union(a,b);
            } else {
                if (find(a) == find(b)){
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }


    }

    static int find(int n) {
        Node node = nodes.get(n);
        if(node.parent == n) {
            return n;
        } else {

            return node.parent = find(node.parent);

        }
    }

    static void union(int a, int b) {
        Node aRoot = nodes.get(find(a));
        Node bRoot = nodes.get(find(b));
        if(aRoot.parent != bRoot.parent){
            bRoot.parent = a;
        }


    }

    static class Node {
        int parent;

        public Node(int parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent +
                    '}';
        }
    }
}
