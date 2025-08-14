package P2042;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2042 {

    static long N, M, K;
    static long[] tree;

    static long a,b,c;


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        int S = 1;
        while( S< N) {
            S *= 2;
        }
        tree = new long[2*S];
        long n;
        for (int i=0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            n = Long.parseLong(st.nextToken());
            tree[S+i] = n;
        }
        Init(1, S, 1);

        long diff;
        long sum;
        for (int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if (a == 1) {
                diff = c - tree[(int) (S+b-1)];
                update(1,S,1, (int) b,diff);

            } else if (a ==2 ) {
                sum = query(1,S,1, (int) b, (int) c);
                bw.write(sum + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static long Init(long left, long right, long node) {

        if (left == right) {
            return tree[(int) node];
        }
        long mid = (left + right) /2;
        long l = Init(left, mid, 2*node);
        long r = Init(mid+1, right, 2*node+1);

        tree[(int) node] = l + r;

        return l + r;
    }

    static void update(long left, long right, long node, long target, long diff) {
        // target 번째 수를 num으로 바꾸는 것.
        // diff는 함수 밖에서 구해줘서 넣기

        if (target >= left && target <= right) {
            tree[(int) node] += diff;
            if(target == left && target == right) {
                return;
            }

            long mid =  ((left + right)/2);
            update(left, mid, node*2, target, diff);
            update(mid+1, right, node*2+1, target, diff);

        }

    }

    static long query(long left, long right, long node, long qLeft, long qRight) {



        if ( left >= qLeft &&  right <= qRight) {
            return tree[(int) node];
        } else if ( left > qRight || right < qLeft) {
            return 0L;
        } else {
            long mid = (left + right) /2;
            long l = query(left, mid, 2*node, qLeft, qRight);
            long r = query(mid+1, right, 2*node+1, qLeft, qRight);
            return l + r;
        }
    }

}
