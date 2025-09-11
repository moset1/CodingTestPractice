package P9934;


import java.util.*;

/*

상근이 방문한 순서대로 줄테니까
이걸 레벨별로 구분해라.

모양은 완전이진 트리다.

2 1 3 이 주어지면

2 3 이 레벨 1,  1 이 레벨 0이다.

그럼 방문 알고리즘을 일단 만든다.
그리고 방문 알고리즘대로 완전 이진 트리를 순회하는데, 몇 번쨰 순서가 어느 레벨인지 기록한다.
그리고 방문 순서를 레벨에 따라 저장한다.


그럼 완전이진 트리를 만들어야하는데

이걸 일단 배열로 만들자.

깊이가 n이면 배열의 크기는 2^n
인덱스 1부터 시작

현재 노드가 k 번째면
왼쪽 자식은  2*k, 오른쪽 자식은 2*k+1

방문 순서 인덱스 에는
0 1 2 3 4 5 6 ..
0 2 1 2

visited
0 1 2 3
f f f f
f   t

go(현재 노드 current, 현재 깊이 depth)

    if( current == 0) {
        return;
    }

    if (isLeaf(current) == false && visited[왼쪽 자식]== false) {
        go(왼쪽 자식, depth+1)
     }
     if ( isLeaf(current) == true  || visited[왼쪽 자식] == true) {
        visited[current] = true;
        order.add(current);

    if (visited[current] == true && isLeaf(current) == false && visited[오른쪽 자식] == false) {
        go(오른쪽 자식, depth+1);
    }

    if (visited[current] == true && (isLeaf(current) == true || (visited[왼쪽] && visited[오른쪽])) {
        go(부모, depth-1);
    }


    왼쪽 자식으로 안 갔으면
        왼쪽 자식으로 간다.
    현재가 왼쪽 자식이 없거나(리프 노프) 또는 왼쪽 자식에 이미 방문했으면
        방문 표시한다.
    현재 빌딩을 방문한 상태이고, 오른쪽 자식을 가지고 있으면 (리프 노드가 아니면)
        오른쪽으로 간다.


     언제 방문 표시를 하는가?
     리프 노드일 때, 왼쪽 자식을 방문했을때

 */
public class P9934 {

    static int K;
    static boolean[] visited;
    static List<Integer> order;
    static int[] record;
    static int size;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        K = sc.nextInt();
        size = 1;
        for (int i=0; i<K; i++) {
            size *= 2;
        }

        visited = new boolean[size];
        order = new ArrayList<>();
        record = new int[size];


        for (int i=1; i< size; i++) {
            record[i] = sc.nextInt();
        }

        inOrder(1, 0);


//        System.out.println(order);
//        System.out.println(Arrays.toString(record));
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i=1; i<size; i++) {
            map.computeIfAbsent(order.get(i-1), k -> new ArrayList<>()).add(record[i]);

        }

        for (int i=0; i< K; i++) {
            for ( int num : map.get(i)) {
                System.out.print(num + " ");
            }
            System.out.println();
        }


    }

    static int leftChild(int current) {
        return 2*current;
    }
    static int rightChild(int current) {
        return 2*current + 1;
    }
    static int parent(int current) {
        return current/2;
    }

    static boolean isLeaf(int current) {
        if(current >= size/2 ) {
            return true;
        } else {
            return false;
        }
    }


    static void inOrder( int current, int depth) {


        if (current >= size) {
            return;
        }

        inOrder(leftChild(current), depth+1);
        order.add(depth);
        inOrder(rightChild(current), depth+1);


    }
}
