package P12869;


import java.util.*;



/*

풀이

SCV 는 1에서 3개다.
체력은 최대 60이다.

즉 완전탐색하면 된다.

한번의 선택하는 경우의 수 6개.

완전 탐색하지 말고, 끝나는 순간 최소인 것으로 하려면 bfs다.

모든 경우의 수를 넣고 횟수까지 저장.

12 10 4
3 개인 경우
9 3 1,  9 1 3, 3 9 1, 3 1 9, 1 9 3, 1 3 9

2 개인 경우
9 3, 3 9

1 개인 경우
9


12 10 4
cnt = 1
3 7 3, 3 9 1, ...

cnt = 2
0 4 2 ...

 */


public class P12869 {

  static int[][][] visited;
  static int N;
  static int[][] ATK = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}};

  public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);

      N = sc.nextInt();

      visited = new int[61][61][61];

      List<Integer> scv = new ArrayList<>();
      for (int i=0; i<N; i++) {
          scv.add(sc.nextInt());
      }

      if (scv.size() == 1) {
          scv.add(0);
          scv.add(0);
      }

      if (scv.size() == 2) {
          scv.add(0);
      }

      scv.sort((o1, o2) -> (o2-o1));

      SCV init = new SCV(scv.get(0), scv.get(1), scv.get(2));
      scv.clear();

      Queue<SCV> queue = new LinkedList<>();

      queue.add(init);
      visited[init.a][init.b][init.c] = 1;


      while(!queue.isEmpty()){

          SCV temp = queue.poll();

          int a = temp.a;
          int b = temp.b;
          int c = temp.c;



          for (int i=0; i<6; i++) {
              int[] atk = ATK[i];
              int na = Math.max(0, a - atk[0]);
              int nb = Math.max(0, b - atk[1]);
              int nc = Math.max(0, c - atk[2]);

              if (na == 0 && nb == 0 && nc == 0) {
                  System.out.println(visited[a][b][c]);
                  return;
              }
              scv.clear();

              scv.add(na);
              scv.add(nb);
              scv.add(nc);
              scv.sort((o1,o2) -> (o2-o1));

              if (visited[scv.get(0)][scv.get(1)][scv.get(2)] >= 1) continue;
              visited[scv.get(0)][scv.get(1)][scv.get(2)] = visited[a][b][c] + 1;

              queue.add(new SCV(scv.get(0), scv.get(1), scv.get(2)));
              // scv.clear(); 여기서 초기화하면 if문에 걸리지 않은 경우는 초기화가 안됨..



          }
      }


  }

  static class SCV {
      int a;
      int b;
      int c;

      public SCV(){}
      public SCV(int a, int b, int c) {
          this.a = a;
          this.b = b;
          this.c = c;
      }
  }




}
