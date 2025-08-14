package P2517;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P2517 {

    static long N;
    static ArrayList<People> people;
    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        int S = 1;
        while(S<N) {
            S*=2;
        }
        tree = new long[2*S];
        people = new ArrayList<>((int) N);
        int current = 1;
        for(int i=0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            people.add(new People(Long.parseLong(st.nextToken()), current++));

        }
        people.sort((o1,o2) -> (int)(o1.ability - o2.ability));
        HashMap<Long, Long> result = new HashMap<>();
        People p;
        long fin;
        for(int i=0; i<people.size(); i++) {
            p = people.get(i);
            update(1,S,1,(int) p.current);
            fin = p.current - query(1,S,1,1,(int)p.current)+1;
            result.put(p.current, fin);
        }

        long num;
        for(long i=1; i<=N; i++) {
            num = result.get(i);
            bw.write((int) num + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void update(int left, int right, int node, int target) {
        if( left <= target && right >= target) {
            tree[node] +=1;

            if (left == right) {
                return;
            }
            int mid = (left + right) /2;
            update(left, mid, 2*node, target);
            update(mid+1, right, 2*node + 1, target);
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {

        if( left >= queryLeft && right <= queryRight) {
            return tree[node];
        } else if (left > queryRight || right < queryLeft) {
            return 0;
        } else {
            if (left == right) {
                return tree[node];
            }
            int mid = (left + right)/2;
            return query(left, mid, node*2, queryLeft, queryRight) + query(mid+1, right, node*2+1, queryLeft, queryRight);
        }
    }



    static class People {
        long ability;
        long current;

        public People(long ability, long current) {
            this.ability = ability;
            this.current = current;
        }

        @Override
        public String toString() {
            return "People{" +
                    "ability=" + ability +
                    ", current=" + current +
                    '}';
        }
    }
}

