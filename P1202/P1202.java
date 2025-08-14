package P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1202 {

    static int N, K;

    static int[] bags;

    static ArrayList<Jewel> jewels;
    static PriorityQueue<Integer> MaxValue;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        jewels = new ArrayList<>(1000000);

        int m, v;

        // 보석 정보 저장
        for (int i=0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));

        }
//       jewels.sort((j1,j2) -> {
//           if(j1.M - j2.M != 0) {
//               return j1.M - j2.M;
//           }
//           return j2.V-j1.V;
//       });


        jewels.sort(Comparator.comparing((Jewel jewel) -> jewel.M)
                .thenComparing(jewel -> jewel.V, Comparator.reverseOrder()));


        // 가방 정보 저장
        bags = new int[K];
        for (int i=0; i< K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }

        // 가방 정렬
        Arrays.sort(bags); // 오름차순 정렬


        // 핵심 알고리즘
        MaxValue = new PriorityQueue<>((o1,o2)-> o2-o1);

        int bag;
        long sum=0;
        int current = 0;
        for(int i=0; i< K; i++) {
            bag = bags[i];


            while(current<jewels.size()) {

                if(jewels.get(current).M > bag) {
                    break;
                }
                MaxValue.add(jewels.get(current).V);
                current++;
                //jewels.remove(0); ArrayList의 삭제의 경우 삭제 후 뒤의 요소들을 앞으로 당겨야한다.
            }

            // 가방이 보석보다 많다면 NullPointer Exception이 생성될 수 있다.
            if(!MaxValue.isEmpty()){
                sum += MaxValue.poll();
            }




        }

        System.out.println(sum);

    }

}

class Jewel {
    int M;
    int V;

    public Jewel(int m, int v) {
        M = m;
        V = v;
    }

    public long getM() {
        return M;
    }


    public long getV() {
        return V;
    }

    @Override
    public String toString() {
        return "Jewel{" +
                "M=" + M +
                ", V=" + V +
                '}';
    }
}
