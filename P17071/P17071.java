package P17071;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P17071 {

    static int N, K;
    static int currentBroPos;

    static Queue<int[]> queue;
    static int[][] visited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        if (N == K) {
            System.out.println(0);
            return;
        }

        queue = new LinkedList<>();
        visited = new int[2][500001];
        currentBroPos = K;
        int currentTime = 0;

        queue.add(new int[] {1, N, 0});
        queue.add(new int[] {0, K, 1});
        boolean find = false;
        visited[0][K] = 1;

        while(!queue.isEmpty()) {

            int[] temp = queue.poll();
            if (temp[0] == 0) {
                currentBroPos = temp[1] + temp[2];
                if (currentBroPos > 500000) {
                    System.out.println(-1);
                    return;
                }
                if (visited[currentTime%2][currentBroPos] != 0) {
                    System.out.println(currentTime+1);
                    return;
                }

                queue.add(new int[] {0, currentBroPos, temp[2]+1});
            }

            if (temp[0] == 1) {
                int pos = temp[1];
               currentTime = Math.max(currentTime, temp[2]);

                for (int next : new int[] {pos-1, pos+1, 2*pos}) {
                    if(next > 500000 || next < 0) continue;
                    if(visited[temp[2]%2][next] != 0) continue;
                    queue.add(new int[] {1, next, temp[2]+1});
                    visited[temp[2]%2][next] = temp[2] + 2;
                }
            }
        }

        if (find == false) {
            System.out.println(-1);
        }
    }
}
