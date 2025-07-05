import java.util.*;
import java.io.*;

class 색종이붙이기 {
    public static void main(String[] args) throws Exception {
        new 색종이붙이기().solution();
    }

            /*
    틀린 이유 
    (1) 모든 경우들을 어떻게 다 방문할지 애매(하지만 순서대로 하면 visited필요 없음)
    (2) 1번에 대한 이유로 백트래킹 부분 어떻게 처리할지 궁금했음 
    (3) 백트래킹 함수를 어떻게 만들고 리스트에서 어떻게 접근해야할지 모르겠었음 (예를 들면 depth 부분)
    이미 채워져있다면 바로 dfs를 해야하는데, 채워져 있으면 아예 호출하지 않고 없으면 dfs를 하도록 함 
    */

    int[][] map;
    List<Node> candidate;
    int N;
    List<Paper> paper;
    int min;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        N = 10;
        min = Integer.MAX_VALUE;
        StringTokenizer st;

        candidate = new ArrayList<>();
        paper = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    candidate.add(new Node(j, i));
                }
            }
        }

        for (int i = 1; i <= 5; i++) {
            paper.add(new Paper(i));
        }

        dfs(0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public void dfs(int depth, int count) {
        if (count >= min) return;
        if (depth == candidate.size()) {
            min = Math.min(min, count);
            return;
        }

        Node cur = candidate.get(depth);
        if (map[cur.y][cur.x] == 0) {
            dfs(depth + 1, count); // 더 탐색해야한다. 
            return;
        }

        for (int i = 4; i >= 0; i--) {
            int size = paper.get(i).type;
            if (paper.get(i).cnt == 0) continue;
            if (isValid(size, cur.x, cur.y)) {
                cover(size, cur.x, cur.y, 0);
                paper.get(i).cnt--;
                dfs(depth + 1, count + 1);
                paper.get(i).cnt++;
                cover(size, cur.x, cur.y, 1); // 되돌리기
            }
        }
    }

    public boolean isValid(int size, int startX, int startY) {
        // 범위를 넘어가면 false
        if (startX + size > N || startY + size > N) return false;

        for (int i = startY; i < startY + size; i++) {
            for (int j = startX; j < startX + size; j++) {
                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }

    public void cover(int size, int x, int y, int value) {
        for (int i = y; i < y + size; i++) {
            
            for (int j = x; j < x + size; j++) {
                map[i][j] = value;
            }
        }
    }

    class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Paper {
        int type;
        int cnt = 5;
        public Paper(int type) {
            this.type = type;
        }
    }
}
