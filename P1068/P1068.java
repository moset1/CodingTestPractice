package P1068;

import org.w3c.dom.Node;

import java.util.*;

public class P1068 {

    static int n;
    static List<Integer>[] tree;
    static int root;
    static int removed;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        tree = new ArrayList[n];
        for (int i=0; i< n; i++) {
            tree[i] = new ArrayList<>();
        }

        root = -1;

        for ( int i=0; i<n; i++) {
            int parent = sc.nextInt();
            if (parent == -1) {
                root = i;
            } else {
                tree[parent].add(i);
            }
        }


        removed = sc.nextInt();

        if (removed == root) {
            System.out.println(0);
            return;
        }

        for (int i=0; i< n; i++) {
            tree[i].remove(Integer.valueOf(removed));
        }

        int leafCount = countLeaf(root);
        System.out.println(leafCount);
    }

    static int countLeaf(int node) {

        if (node == removed) return 0;

        if (tree[node].isEmpty()) {
            return 1;
        }

        int count = 0;
        for (int child : tree[node]) {
            count += countLeaf(child);
        }

        return count;

    }
}
