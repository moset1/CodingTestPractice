package P10709;

import java.util.Scanner;

public class P10709 {

    static char[][] map;
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int H = sc.nextInt();
        int W = sc.nextInt();

        map = new char[H][W];

        for(int i=0; i<H; i++) {
            String str = sc.next();
            for(int j=0; j<W; j++) {

                map[i][j] = str.charAt(j);
            }
        }

        // 구름이 있으면 0부터 시작, 그다음 구름까지 +1
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<H; i++) {
            boolean cloud = false;
            int num = 0;
            for(int j=0; j<W; j++) {
                if(map[i][j] == 'c'){
                    cloud = true;
                    num = 0;
                    sb.append(num + " ");
                    num++;
                }else if(map[i][j] != 'c' && cloud == false) {
                    sb.append(-1);
                    sb.append(" ");
                } else {
                    sb.append(num + " ");
                    num++;
                }
            }

            sb.append("\n");
        }
        String answer = new String(sb);
        System.out.println(answer);

    }
}
