package P1781;

import java.util.*;
/*

데드라인 오름 차순

pq : 컵라면 수 오름차순

데드라인에 맞게 pq에 문제 넣기 {문제 번호, 데드라인, 컵라면 수}
단위시간만큼만 남기기
 */
public class P1781 {

    static List<int[]> problemInfo;

    static int n, d, r;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        problemInfo = new ArrayList<>();
        for(int i=0; i<n; i++) {
            d = sc.nextInt();
            r = sc.nextInt();
            problemInfo.add(new int[] {i+1, d, r});
        }

        problemInfo.sort((o1,o2) -> o1[1] - o2[1]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> (o1[2] - o2[2]));

        int index = 0;
        for (int day=1; day<=problemInfo.get(problemInfo.size()-1)[1];day++) {

            while(index < problemInfo.size() && problemInfo.get(index)[1] <= day) {
                pq.add(problemInfo.get(index));
                index++;
            }

            while(pq.size() > day) {
                pq.poll();
            }
        }

        long sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.poll()[2];

        }
        System.out.println(sum);

    }
}
