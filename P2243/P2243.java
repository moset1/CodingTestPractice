package P2243;

import java.io.*;
import java.util.StringTokenizer;

public class P2243 {

    static int S;
    static int n;
    static int A,B,C;
    static int[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(st.nextToken());

        S = 1;
        while(S<1000000) {
            S *= 2;
        }
        tree = new int[2*S];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            if ( A == 1) {
                B = Integer.parseInt(st.nextToken());
                int t = query(1, S, 1, B);
                bw.write(t+"\n");

                update(1, S, 1, t, -1);
            } else if (A == 2) {
                B = Integer.parseInt(st.nextToken());
                C = Integer.parseInt(st.nextToken());

                update(1, S, 1, B, C);


            }
        }
        bw.flush();
        bw.close();
    }


    static void update(int left, int right, int node, int target, int diff) {

        if ( target >= left && target <= right) {
            //트리의 node 값을 일단 업데이트
            tree[node] += diff;

            // 종료 조건
            if(left == target && right == target) {
                return;
            }

            int mid = (left + right) /2;
            update(left, mid, node*2, target, diff);
            update(mid+1, right, node*2+1, target, diff);

        }


    }

    static int query(int left, int right, int node, int target) {

        // left, right로 사탕 맛의 범위를 좁혀가면서 찾는 것
        if(left == right) {
            return left;
        }

        int mid = (left +right) /2;
        // tree[2*node], tree[2*node +1] 과 target을 비교
        if (tree[2*node] >= target) {
            return query(left, mid, node*2, target);
        } else {
            target -= tree[2*node];
            return query(mid+1, right, node*2+1, target);
        }
    }

}
