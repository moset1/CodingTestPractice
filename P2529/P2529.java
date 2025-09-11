package P2529;


import java.util.ArrayList;
import java.util.Scanner;

/*

9!

부등호에 따라서 어떤 수를 넣을지 결정해야한다.

처음엔 0-9 까지 모두 넣고 시작


if (idx == k) {
    result를 숫자로 변환
    min, max 값 업데이트
    return
}

다음 부등호가 '<'면 현재수보다 큰 수들만 조회? '>'면 작은 수들만 조회
visited continue

visited[next] = true
result.add()
go (num, idx)  : idx는 부등호 배열의 인덱스
visited[next] = false
result.remove()

 */
public class P2529 {

    static int k;
    static char[] ops;
    static boolean[] visited;
    static ArrayList<Integer> result;
    static long max = 0;
    static long min = Long.MAX_VALUE;
    static String smax = "0";
    static String smin = "10000000000";

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        k = sc.nextInt();

        ops = new char[k];
        visited = new boolean[10];
        result = new ArrayList<>();


        for (int i=0; i<k; i++) {
            char temp = sc.next().charAt(0);

            ops[i] = temp;
        }

        for (int i=0; i<=9; i++) {
            visited[i] = true;
            result.add(i);
            go(i, 0);
            visited[i] = false;
            result.remove(result.size()-1);
        }

//        System.out.println(max);
//        System.out.println(min);



        System.out.println(smax);
        System.out.println(smin);
    }
    static void go(int num, int idx) {


        if (result.size() == k+1) {
            // 숫자로 변환

            long temp = 0;
            for (int n : result) {
                temp *= 10;
                temp += n;
            }

            max = Math.max(max, temp);
            min = Math.min(min, temp);

            StringBuilder stemp = new StringBuilder();
            for (int n: result) {
                stemp.append(n);
            }

            if (Long.parseLong(stemp.toString()) > Long.parseLong(smax)) {
                smax = stemp.toString();
            }
            if (Long.parseLong(stemp.toString()) < Long.parseLong(smin)) {
                smin = stemp.toString();
            }

            return;
        }

        if(ops[idx] == '<') {
            for (int i=num+1; i<=9; i++) {
                if (visited[i] ) continue;
                visited[i] = true;
                result.add(i);
                go(i, idx+1);
                visited[i] = false;
                result.remove(result.size()-1);
            }
        }
        else if ( ops[idx] == '>') {
            for (int i=0; i< num; i++) {
                if (visited[i] ) continue;
                visited[i] = true;
                result.add(i);
                go(i, idx+1);
                visited[i] = false;
                result.remove(result.size()-1);
            }
        }
    }

}

