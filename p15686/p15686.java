package p15686;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p15686 {

    static int n, m;
    static ArrayList<Integer> comb;
    static int[][] map;
    static ArrayList<int[]> house;
    static ArrayList<int[]> chicken;
    static List<List<int[]>> output;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        comb = new ArrayList<>();
        output = new ArrayList<>();
        map = new int[n+1][n+1];
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j]== 1) house.add(new int[] {i, j});
                if (map[i][j] == 2) chicken.add(new int[]{i,j});
            }
        }


        int min = Integer.MAX_VALUE;

        for(int i=1; i<=m; i++) {

            output = new ArrayList<>();

            List<int[]> ret = new ArrayList<>();

            combination(chicken, ret, 0, i);



            for (List<int[]> o : output) {
                int total = 0;

                for (int[] h : house) {

                    int dis = Integer.MAX_VALUE;
                    for (int[] c : o) {

                        dis = Math.min( Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]), dis);

                    }

                    total += dis;
                }
                min = Math.min(min, total);
            }


        }



        System.out.println(min);
    }

    static void combination(List<int[]> arr, List<int[]> ret, int start, int r) {

        if(r==0) {
            output.add(new ArrayList<>(ret));
            return;
        }

        for (int i=start; i<arr.size(); i++) {
            ret.add(arr.get(i));
            combination(arr, ret, i +1, r-1);
            ret.remove(ret.size() -1);
        }

    }


    static void combi (ArrayList<int[]> arr, List<int[]> ret, int start, int r) {
        if( r == 0) {
            output.add(new ArrayList<>(ret));
            return;
        }

        for (int i=start; i<arr.size(); i++) {
            ret.add(arr.get(i));
            combination(arr, ret, i+1, r-1);
            ret.remove(ret.size() -1);
        }
    }

}
