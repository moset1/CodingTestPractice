package P1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1062 {

    static int N, K;
    static boolean[] visited;
    static String[] words;
    static int selectedCount = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 5;

        visited = new boolean[26];

        visited['a' - 'a' ] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        words = new String[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
        }
        if(K>0) {
            for (int i=0; i< 26; i++) {
                if (visited[i] == false) {
                    dfs(i);
                }
            }
        } else if (K==0) {
           max = countWords();
        }

        System.out.println(max);
    }

    static void dfs(int index) {

        // 1. 체크인
        visited[index] = true;
        selectedCount++;

        // 2. 목적지인가?
        if (selectedCount == K) {
            max = Math.max(countWords(), max);
        } else {
            // 3. 연결된 곳을 순회 - 현재보다 다음 것 ~Z
            for (int next = index + 1 ; next < 26; next++) {
                // 4. 갈 수 있는가? - 방문한적 없으면
                if(visited[next] == false) {
                    // 5. 간다 -dfs 호출
                    dfs(next);
                }
            }
        }

        //6. 체크아웃
        visited[index] = false;
        selectedCount--;
    }

    static int countWords() {
        int count = 0;
        for(int i=0; i<N; i++) {
            boolean isPossible = true;
            String word = words[i];
            for (int j =0; j<word.length(); j++) {
                if (visited[word.charAt(j) - 'a'] == false) {
                    isPossible = false;
                    break;
                }
            }

            if(isPossible == true) {
                count++;
            }
        }
        return count;
    }
}
