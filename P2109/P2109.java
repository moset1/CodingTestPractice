package P2109;

import java.util.*;


/*
d일 안에 오면 p를 주겠다.

1. 무식하게

날짜 순으로 정렬
d일 안에 있는 걸 pq에 넣기.
=> 뽑았을 때 d일이 지났으면 안하면 된다.
=> 아니다. 더큰 값이 다른 날에 있을 경우 작은 건 포기할 수 있다.


우디르급 태세전환!

그냥 크기 별로 정렬인경우
=> 시간이 넉넉한데도 먼저 처리할 수 있다.

우디르!

날짜별로 오름차순, 크기 오름차순? =>
최소를 작게 만들기
날짜 별로 pq에 넣고,
날짜 크기만큼만 남기고 모두 없애기


 */
public class P2109 {

    static int n, p, d;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        pq = new PriorityQueue<>((o1,o2) -> o1[0] - o2[0]);  // {p, d}

        LinkedList<int[]> list = new LinkedList<>();

        for (int i=0; i<n; i++) {
            p = sc.nextInt();
            d = sc.nextInt();

            list.add(new int[] {p,d});
        }
        list.sort((o1, o2) -> (o1[1] - o2[1])); // d 크기 오름차순

        int sum = 0;

        int day = 1;

        while (!list.isEmpty()) {


            while (!list.isEmpty() && day == list.getFirst()[1]) {
                pq.add(list.pollFirst());
            }

            while(pq.size() > day) {
                pq.poll();
            }

            day++;

        }

        while(!pq.isEmpty()) {
            sum += pq.poll()[0];
        }
        System.out.println(sum);

    }
}
