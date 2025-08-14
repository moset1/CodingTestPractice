package P9202;

import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class P9202 {

    static int w, b;
    static String[][] board;
    static boolean[][] visited;
    static Node root;

    static TreeSet<String> result;
    static final int[] MX = {-1, 1, 0, 0, 1, 1, -1, -1};
    static final int[] MY = {0,0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        root = new Node("" );


        w = Integer.parseInt(st.nextToken());

        String word;
        for (int i=0; i< w; i++) {
            st = new StringTokenizer(br.readLine());
            word = st.nextToken();
            registerWord(word, root);
        }
        st = new StringTokenizer(br.readLine());

        board = new String[4][4];

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        for(int i=0; i<b; i++) {
            result = new TreeSet<>();
            for(int j=0; j<4; j++) {
                st = new StringTokenizer(br.readLine());

                String line = st.nextToken();
                for (int k=0; k<4; k++) {
                    board[j][k] =line.substring(k, k+1);
                }
            }

            for(int j=0; j<4; j++) {
                System.out.println(Arrays.toString(board[j]));
            }

            for(int j=0; j<4; j++) {
                for (int k=0; k<4; k++) {
                    String s = board[j][k];
                    visited = new boolean[4][4];
                    DFS(k,j, root, "");
                }
            }

//            System.out.println(result);

            int score = 0;
            String longWord= "";
            int count = result.size();
            // 정답 출력
            for(String a : result) {

                switch (a.length()) {
                    case 1:
                        score+=0;
                        break;
                    case 2:
                        score+=0;
                        break;
                    case 3,4:
                        score+=1;
                        break;
                    case 5:
                        score+=2;
                        break;
                    case 6:
                        score+=3;
                        break;
                    case 7:
                        score+=5;
                        break;
                    case 8:
                        score+=11;
                        break;
                }
                if ( a.length() > longWord.length()){
                    longWord = a;
                } else if (a.length() == longWord.length()) {
                    String[] strings = {a, longWord};
                    Arrays.sort(strings);
                    longWord = strings[0];
                }
            }

            bw.write(score + " " + longWord + " " + count + "\n");
            if(i<b-1) {
                st =  new StringTokenizer(br.readLine());
            }

        }
        bw.flush();
        bw.close();



    }

    static class Node {
        String data;
        ArrayList<Node> child;
        boolean isWord;

        public Node(String data) {
            this.data = data;
            this.isWord = false;
            this.child = new ArrayList<>();
        }

        public Node(String data, boolean isWord) {
            this.data = data;
            this.isWord = isWord;
            this.child = new ArrayList<>();
        }

        public Node findNode( String s) {
            for(int i=0; i<this.child.size(); i++) {
                if(s.equals(this.child.get(i).data) ) {
                    return this.child.get(i);
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    ", child=" + child +
                    ", isWord=" + isWord +
                    '}';
        }
    }

    static void registerWord(String word, Node node) {

        if( word.length() <1) {
            return;
        }
        char c = word.charAt(0);

        boolean flag = false;
        for(int i=0; i<node.child.size(); i++) {
            Node child = node.child.get(i);
            if(child.data.charAt(0) == c) {
                flag = true;
                registerWord(word.substring(1), child);
            }
        }
        // child에서 c를 못 찾은 경우
        if(flag==false) {

            if(word.length() == 1) {

                node.child.add(new Node(Character.toString(c), true));



            } else if(word.length() > 1) {
                node.child.add(new Node(Character.toString(c)));
                Node child = node.findNode(Character.toString(c));
                registerWord(word.substring(1), child);
            }
        }

    }

    static void DFS(int x, int y, Node node, String word) {
        visited[y][x] = true;

        // 트라이를 순회
        for(int i=0; i<node.child.size(); i++) {
            if(node.child.get(i).data.equals(board[y][x])){
                node = node.findNode(board[y][x]);
                word = word + board[y][x];
            }
        }
        if(node.isWord == true) {
            result.add(word);
        }
        // board를 순회
        for(int i=0; i<8; i++) {
            int mx = x + MX[i];
            int my = y + MY[i];
            if(0<=mx && mx <= 3 && 0<=my && my<=3 && visited[my][mx] == false) {
                for(int j=0; j<node.child.size(); j++) {
                    String s = node.child.get(j).data;
                    if(s.equals(board[my][mx])) {
                        DFS(mx, my, node, word );
                    }
                }
            }
        }
    }
}
