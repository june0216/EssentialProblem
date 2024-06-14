import java.io.*;
import java.util.*;

public class 나무재태크 {
    public static void main(String[] args) throws Exception {
        new 나무재태크().solution();
    }

    static int[] dx = {1, 0, -1, 0, 1, -1, -1, 1};
    static int[] dy = {0, -1, 0, 1, 1, -1, 1, -1};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N*N 크기
        int M = Integer.parseInt(st.nextToken()); // 나무의 개수
        int K = Integer.parseInt(st.nextToken()); // K년이 지난 후

        int[][] ground = new int[N + 1][N + 1]; // 땅의 양분 상태
        int[][] A = new int[N + 1][N + 1]; // 겨울에 추가되는 양분의 양

        // 초기 땅 양분 설정
        for (int i = 1; i <= N; i++) {
            Arrays.fill(ground[i], 5); // 처음 모든 칸에 양분 5
        }

        // 겨울에 추가될 양분 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무 정보 입력 (나무 나이 작은 순서로 관리되어야 함)
        Deque<Tree> tree_list = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            tree_list.add(new Tree(x, y, age));
        }

        // K년 동안 반복
        while (K-- > 0) {
            Queue<Tree> die_tree_list = new LinkedList<>(); // 봄에 죽은 나무들

            // 봄: 나이가 어린 나무부터 처리 (나무가 있는 위치에서 양분 섭취)
            for (int i = 0; i < tree_list.size(); ) {
                Tree cur = tree_list.poll(); // 나무를 하나씩 꺼내서 처리
                if (ground[cur.x][cur.y] >= cur.age) {
                    ground[cur.x][cur.y] -= cur.age; // 나이가 어린 나무부터 양분을 먹음
                    cur.age++; // 나무의 나이 증가
                    tree_list.add(cur); // 다시 나무를 큐에 추가
                    i++; // 양분을 먹은 경우에만 i 증가
                } else {
                    die_tree_list.add(cur); // 양분을 못 먹은 나무는 죽음
                }
            }

            // 여름: 봄에 죽은 나무들이 양분이 됨
            for (Tree t : die_tree_list) {
                ground[t.x][t.y] += t.age / 2; // 죽은 나무의 나이 / 2 만큼 양분으로 변환
            }

            // 가을: 나무 번식 (나이가 5의 배수인 나무만 번식)
            Queue<Tree> temp_list = new LinkedList<>();
            for (Tree t : tree_list) {
                if (t.age % 5 == 0) {
                    temp_list.add(t); // 번식할 나무들을 따로 저장
                }
            }
            while (!temp_list.isEmpty()) {
                Tree t = temp_list.poll();
                for (int d = 0; d < 8; d++) {
                    int nx = t.x + dx[d];
                    int ny = t.y + dy[d];
                    if (nx >= 1 && ny >= 1 && nx <= N && ny <= N) {
                        tree_list.addFirst(new Tree(nx, ny, 1)); // 번식한 나무는 나이 1
                    }
                }
            }

            // 겨울: 각 땅에 양분 추가
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    ground[i][j] += A[i][j]; // 겨울마다 주어지는 양분을 더해줌
                }
            }
        }

        // 살아남은 나무의 개수 출력
        System.out.println(tree_list.size());
    }

    class Tree implements Comparable<Tree> {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age; // 나이가 어린 순서대로 정렬
        }
    }
}
