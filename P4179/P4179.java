package P4179;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P4179 {

    static int R,C;
    static int[][] map;

    static int[] MR = {0, 0, -1, 1};
    static int[] MC = {-1, 1, 0, 0};

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new int[R][C];

        int[] Jstart = {0};
        int[] Fstart = {0};
        char[] chars;
        for (int i=0; i<R; i++) {
            chars = sc.next().toCharArray();

            for (int j=0; j<C; j++) {

                if(chars[j] == '#') map[i][j] = 1;
                if(chars[j] == '.') map[i][j] = 0;
                if(chars[j] == 'J') {
                    map[i][j] = 2;
                    Jstart = new int[] {i, j};
                }
                if(chars[j] == 'F') {
                    map[i][j] = 3;
                    Fstart = new int[] {i, j};
                }

            }
        }


        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        // 불은 여러개 들어올 수 있다.

        for (int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if (map[i][j] == 3) {
                    queue.add(new int[] {i, j, 3});

                }
            }
        }

        queue.add(new int[] {Jstart[0], Jstart[1], 2, 0});

        visited[Jstart[0]][Jstart[1]] = true;
        boolean exit = false;
        int ret = 0;
        int [] temp;
        int nr, nc;



        while(!queue.isEmpty()) {

            temp = queue.poll();

            for (int i=0; i<4; i++) {
                nr = temp[0] + MR[i];
                nc = temp[1] + MC[i];

                if (temp[2] == 3) {  // 불이면
                    if(nr < 0 || nc < 0 || nr >=R || nc >= C) continue;
                    if(map[nr][nc] == 1 || map[nr][nc] == 3) continue;
                    map[nr][nc] = 3;
                    queue.add(new int[] {nr, nc, 3});
                }

                if (temp[2] == 2) {

                    if(nr < 0 || nc < 0 || nr >=R || nc >= C) {
                        ret = temp[3]+1;
                        exit = true;
                        break;
                    }
                    if (map[nr][nc] == 1 || map[nr][nc] == 3) continue;
                    if (visited[nr][nc] == true) continue;

                    queue.add(new int[] {nr, nc, 2, temp[3]+1});
                    visited[nr][nc] = true;

                }
            }

            if(exit == true) {
                break;
            }

        }

        if (!exit) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(ret);
        }
    }



}

/*
4 4
####
#J.F
#...
#F..

IMPOSSIBLE



4 4
####
#J..
#.F.
#...


5 6
######
#J...#
#....#
#.....
#.....
 */