package P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_P1806_부분합 {

    static int low;
    static int high;
    static int[] numArray;
    static int sum;
    static boolean LI;
    static int least_length;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        numArray = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        low = 0;
        high = 0;
        sum = numArray[0];
        LI = false;
        least_length = 100000;


        // high가 끝 인덱스에 올 때까지의 과정
        while(!LI) {
            if (sum < S) {
                if (LI == false) {
                    high++;
                    sum += numArray[high];
                }
            } else {
                if((high-low+1) < least_length) {
                    least_length = high-low+1;
                }
                if(low<high) {

                    sum -= numArray[low];
                    low++;
                } else {
                    //low==high일 때, 즉 least_length=1이라 종료
                    break;
                }

            }

            if (high == N -1) {
                LI = true;
            }


        }

        // high가 끝 인덱스에 오고나서의 과정
        while(high==N-1) {
            if (sum<S) {
                break;
            } else {
                if((high-low+1) < least_length) {
                    least_length = high-low+1;
                }

                sum -= numArray[low];
                low++;
            }
        }

        if(least_length==100000) {
            System.out.println(0);
        }else {
            System.out.println(least_length);
        }

    }
}
