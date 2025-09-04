package P12851;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P12851 {


    static int N, K;
    static Queue<Integer> queue;
    static int[] visited;
    static int[] cnt;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        queue = new LinkedList<>();
        visited = new int[200001];
        cnt = new int[200001];



        if (N >= K) {
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

        queue.add(N);

        visited[N] = 1;
        cnt[N] = 1;
        int findLev = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {

            int temp = queue.poll();

            for (int next : new int[] {temp-1, temp+1, temp*2}) {

                if (next < 0 || next > 200000) continue;
                if (next == K) {
                    findLev = visited[temp]+1;
                }
                if (visited[next] == 0) {
                    visited[next] = visited[temp] + 1;
                    if(visited[next] > findLev) {
                        queue.clear();
                        break;
                    }

                    cnt[next] += cnt[temp];
                    queue.add(next);
                } else {
                    if (visited[next] == visited[temp] + 1) {
                        cnt[next]+= cnt[temp];
                    }
                }
            }
        }

        System.out.println(visited[K]-1);
        System.out.println(cnt[K]);

    }
}
