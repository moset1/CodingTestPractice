package P13913;

import java.util.*;

public class P13913 {


    static int N, K;
    static Queue<Integer> queue;
    static int[] visited;
    static int[] cnt;
    static int[] prev;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        queue = new LinkedList<>();
        visited = new int[200001];
        cnt = new int[200001];
        prev = new int[200001];
        StringBuilder sb = new StringBuilder();

        List<Integer> result = new ArrayList<>();

        if (N >= K) {
            System.out.println(N-K);
            for (int i=N; i>=K; i--) {
                sb.append(i);
                sb.append(" ");
            }

            System.out.println(sb);
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
                    prev[next] = temp;
                } else {
                    if (visited[next] == visited[temp] + 1) {
                        cnt[next]+= cnt[temp];
                    }
                }
            }
        }


        int temp = K;
        result.add(K);
        do {
            temp = prev[temp];
            result.add(temp);
        } while (temp != N);

        for (int i=result.size(); i >0; i--) {
            sb.append(result.get(i-1));
            sb.append(" ");
        }
        System.out.println(visited[K]-1);
        System.out.println(sb);

    }
}
