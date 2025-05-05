import java.util.*;
import java.io.*;

public class 영역칠하기 {

    static int[][] map;
    static boolean[][] visited;
    static int rows, cols;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int areaSize;

    public static void main(String[] args) throws IOException {
        new 영역칠하기().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[rows][cols];
        visited = new boolean[rows][cols];

        // 사각형 채우기
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            for (int y = startY; y < endY; y++) {
                for (int x = startX; x < endX; x++) {
                    map[y][x] = 1;
                }
            }
        }

        List<Integer> areaSizes = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (map[y][x] == 0 && !visited[y][x]) {
                    areaSize = 0;
                    dfs(x, y);
                    areaSizes.add(areaSize);
                }
            }
        }

        Collections.sort(areaSizes);
        StringBuilder sb = new StringBuilder();
        sb.append(areaSizes.size()).append("\n");
        for (int size : areaSizes) {
            sb.append(size).append(" ");
        }
        System.out.println(sb);
    }

    public void dfs(int x, int y) {
        visited[y][x] = true;
        areaSize++;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isInBounds(nextX, nextY) && map[nextY][nextX] == 0 && !visited[nextY][nextX]) {
                dfs(nextX, nextY);
            }
        }
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < cols && y < rows;
    }
}
