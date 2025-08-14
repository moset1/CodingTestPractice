package P2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class B_P2805_나무자르기 {

    static long N, M;
    static long start, end, mid;
    static ArrayList<Long> trees;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());

        M = Long.parseLong(st.nextToken());

        trees = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            trees.add(Long.parseLong(st.nextToken()));
        }

        long max = Collections.max(trees);
        start = 0;
        end = max;
        mid = (start + end) /2;

        long length;
        while(start<end) {
            length = getH(trees, mid);
            if ( length == M) {
                System.out.println(mid);
                break;
            } else if ( length > M) {
                start = mid + 1;
                mid = (start + end) / 2;
            } else {
                end = mid -1;
                mid = (start + end) /2;
            }

        }
        if (start == end) {
            length = getH(trees, start);
            if (length < M) {
                System.out.println(start -1);
            } else{
                System.out.println(start);
            }
        }


    }

    static long getH(ArrayList<Long> trees, long mid) {

        long sum = 0;
        for (Long tree : trees) {
            if (tree > mid) {
                sum += tree - mid;
            }
        }

        return sum;
    }
}
