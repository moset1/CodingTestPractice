package P1715;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_1713_후보_추천하기 {

    static ArrayList<Candidate> candidates;
    static int[] isCandidate;
    static int time;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        isCandidate = new int[101];


        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        candidates = new ArrayList<>();

        int n;
        for(int time=1; time<=K; time++) {

            n = Integer.parseInt(st.nextToken());
            if (isCandidate[n] == 0) {
                isCandidate[n]++;
            } else {
                isCandidate[n]++;
                // 사진틀에서 찾아서 count++
                for (int i=0; i<candidates.size(); i++) {
                    if (candidates.get(i).num == n) {
                        candidates.get(i).count++;

                        candidates.sort(Comparator.comparing(Candidate::getCount, Comparator.reverseOrder())
                                .thenComparing(Candidate::getSt_time, Comparator.reverseOrder()));

                    }
                }
            }

            if (isCandidate[n] == 1) {
                if (candidates.size()==N) {

                    isCandidate[candidates.get(N-1).num] = 0;
                    candidates.remove(N-1);

                    candidates.add(new Candidate(n, isCandidate[n], time ));
                    candidates.sort(Comparator.comparing(Candidate::getCount, Comparator.reverseOrder())
                            .thenComparing(Candidate::getSt_time, Comparator.reverseOrder()));




                } else {

                    candidates.add(new Candidate(n, isCandidate[n], time ));
                    candidates.sort(Comparator.comparing(Candidate::getCount, Comparator.reverseOrder())
                            .thenComparing(Candidate::getSt_time, Comparator.reverseOrder()));

                }
            }

//            System.out.println(time + "번 쨰 : " + candidates );

        }

        candidates.sort((o1, o2) -> o1.num- o2.num);

        for (int i=0; i<candidates.size(); i++) {
            bw.write(String.valueOf(candidates.get(i).num));
            bw.write(" ");
        }
        bw.flush();
        bw.close();






    }

}

class Candidate {

    int num;
    int count;
    int st_time;

    public Candidate(int num, int count, int st_time) {
        this.num = num;
        this.count = count;
        this.st_time = st_time;
    }

    public int getNum() {
        return num;
    }

    public int getCount() {
        return count;
    }

    public int getSt_time() {
        return st_time;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "num=" + num +
                ", count=" + count +
                ", st_time=" + st_time +
                '}';
    }
}