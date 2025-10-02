package P1202;


import java.util.*;

/*
보석 jewel : weight M, value V

가방 bag  K 개, 각 가방이 담을 수 있는 최대 무게 C
가방은 하나의 보석만 담을 수 있다.

최댓값 : 최대를 크게, 최소를 작게

각 가방에 가방이 담을 수 있는 최대 무게의 보석을 담아야한다.

가방을 오름차순 정렬
보석을 무게를 기준으로 오름차순 정렬

pq에 보석을 담는데, 현재 가방의 무게보다 가벼운 보석을 가치를 기준으로 내림차순 정렬

=> 현재 가방에 담을 수 있는 최대 무게의 보석을 꺼낼 수 있다.




 */
public class P1202_2 {

    static int n, k;
    static List<int[]> jewels;
    static List<Integer> bags;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        jewels = new ArrayList<>();
        bags = new ArrayList<>();
        pq = new PriorityQueue<>((o1,o2) -> o2[1] - o1[1]);

        int m, v, c;
        for(int i=0; i<n; i++) {
            m = sc.nextInt();
            v = sc.nextInt();
            jewels.add(new int[] {m,v});
        }
        for(int i=0; i<k; i++) {
            c = sc.nextInt();
            bags.add(c);
        }

        jewels.sort((o1,o2) -> o1[0] - o2[0]);

        bags.sort((o1,o2) -> o1-o2);

        int index = 0;
        long sum = 0;
        for( int bag : bags) {

            while (index < jewels.size() && jewels.get(index)[0] <= bag) {
                pq.add(jewels.get(index));
                index++;
            }
            if (pq.isEmpty()) continue;
            sum += pq.poll()[1];

        }

        System.out.println(sum);

    }
}
