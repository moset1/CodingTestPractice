package P16234;

import java.util.*;

public class P16234 {


    static int N, L, R;
    static int[][] map;
    static int[][] united;
    static ArrayList<int[]> resultMap;
    static int[] MR = {-1, 1, 0, 0};
    static int[] MC = {0, 0, -1, 1};

    static boolean flag;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();


        map = new int[N][N];
        united = new int[N][N];

        resultMap = new ArrayList<>();

        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // unite 테스트

        int cnt = 0;
        while (true) {

            resultMap.clear();
            united = new int[N][N];   // 초기화 안해서 오래걸림
            int num = 1;
            flag = false; // 인구 이동 일어나는지


            resultMap.add(new int[2]);
            for(int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if(united[i][j] == 0) {
                        unite(i, j, num);
                        num++;
                        resultMap.add(new int[2]);
                    }
                }
            }
            // 종료 조건
            if (flag == false) break;

            cnt++;

            addUnitedPopulation();

            moveUnitedPopulation();


        }

        System.out.println(cnt);



//        for(int i=0; i<N; i++) {
//            for(int j=0; j<N; j++) {
//                System.out.print(united[i][j] + " ");
//            }
//            System.out.println();
//        }



    }




    static void addUnitedPopulation() {

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                resultMap.get(united[i][j])[0] += map[i][j];
                resultMap.get(united[i][j])[1]++;

            }
        }
    }

    static void moveUnitedPopulation() {

        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int sum = resultMap.get(united[i][j])[0];
                int count = resultMap.get(united[i][j])[1];
                map[i][j] = sum/count;
            }
        }
    }

    static void unite(int r, int c, int num) {

        united[r][c] = num;

        for (int i=0; i<4; i++) {
            int nr = r + MR[i];
            int nc = c + MC[i];
            if (!inRange(nr, nc)) continue;
            if (united[nr][nc] != 0) continue;
            if (!betweenLR(map[r][c], map[nr][nc])) continue;

            flag = true;
            unite(nr, nc, num);



        }


    }

    static boolean inRange( int nr, int nc) {
        if (nr < 0 || nc < 0 || nr >= N || nc >= N ) return false;

        return true;
    }

    static boolean betweenLR ( int a, int b) {
        int temp = Math.abs(a-b);

        if(temp >= L && temp <= R) return true;
        return false;
    }
}
