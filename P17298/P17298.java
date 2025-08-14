package P17298;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class P17298 {

    static int n;

    static int[] numArr;
    static int NGE = 0;
    static int[] ngeArr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        numArr = new int[n];
        ngeArr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new LinkedList<>();

        deque.push(0);

        for (int i=1; i<n; i++) {
            int cur = numArr[i];
            while(!deque.isEmpty() && numArr[deque.peek()] < cur) {
                ngeArr[deque.pop()] = cur;
            }
            deque.push(i);
        }

        while(!deque.isEmpty()) {
            ngeArr[deque.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            sb.append(ngeArr[i]).append(" ");
        }
        System.out.println(sb);
    }



}
