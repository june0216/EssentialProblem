import java.io.*;
import java.util.*;
public class 현대모비스자율주행시스템 {

    static final int EMPTY = 0, WALL = 1, MAX = 987654321;

    static int R, C,  patternCnt;
    static int[][] map;
    static boolean[][][] visit;
    static ArrayList<Node> path = new ArrayList<>();
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        patternCnt = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        // visit 배열을 3차원으로 한다. 패턴 사용 횟수를 2배 증가하여 0~패턴횟수까지는 중간 지점 방문하지 않는 경우의 케이스를 저장하고
        // 패턴 횟수 +1 ~ 2배까지는 중간 지점 방문한 적이 있는 케이스들을 관리한다.
        // 이렇게 2배로 하던가 아니면 4차원으로 하나의 차원은 중간 지점 방문 여부를 관리할 수도 있다.
        visit = new boolean[R][C][(patternCnt + 1) * 2];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    path.add(new Node(i-2, j-2));
                }
            }
        }

        System.out.println(bfs(new LinkedList<>(Arrays.asList(new Node(0, 0, patternCnt, 0, false)))));
    }

    private static int bfs(Deque<Node> que) {
        visit[0][0][patternCnt] = true;
        while (!que.isEmpty()) {
            Node cur = que.poll();
            if (cur.isPassMid && cur.x == R - 1 &&cur.y == C - 1) {
                return cur.distance;
            }

            // 상하좌우로 이동해보기
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                // 지도 안에 있고 벽이 아니며 방문한 적이 없다면 큐에 넣는다.
                if (checkRange(nx, ny) && map[nx][ny] != WALL && !visit[nx][ny][cur.count]) {
                    visit[nx][ny][cur.count] = true;
                    if (cur.isPassMid) { // 중간 지점을 방문한 적이 있다면
                        // 거리를 증가한 후 넣는다.
                        que.add(new Node(nx, ny, cur.count, cur.distance + 1, cur.isPassMid));
                    } else { // 중간 지점을 방문한 적이 없다면
                        // 큐에 넣을 좌표가 지금 2인지 아닌지 확인 -> 2라면 isPassMid변수를 변경하고 아니면 그대로 넣는다.
                        visit[nx][ny][map[nx][ny] == 2 ? cur.count + patternCnt + 1 : cur.count] = true;
                        que.add(new Node(nx, ny, map[nx][ny] == 2 ? cur.count + patternCnt + 1 : cur.count, cur.distance + 1,
                                map[nx][ny] == 2 ? true : cur.isPassMid));
                    }
                }
            }

            // 패턴을 사용하는 경우도 다 살펴보자
            if (cur.isPassMid ? cur.count > patternCnt + 1 : cur.count > 0) {
                for (Node p : path) {
                    // 패턴 사용해서 이동한 위치
                    int nx = cur.x + p.x;
                    int ny = cur.y + p.y;
                    if (checkRange(nx, ny) && map[nx][ny] != WALL && !visit[nx][ny][cur.count - 1]) {

                        visit[nx][ny][cur.count - 1] = true;
                        if (cur.isPassMid) {
                            que.add(new Node(nx, ny, cur.count - 1, cur.distance + 1, cur.isPassMid));
                        } else {
                            visit[nx][ny][cur.count + patternCnt] = true;
                            que.add(new Node(nx, ny, map[nx][ny] == 2 ? cur.count + patternCnt : cur.count - 1, cur.distance+ 1,
                                    map[nx][ny] == 2 ? true : cur.isPassMid));
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static boolean checkRange(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < R && ny < C;
    }


    private static class Node {
        int x, y, count, distance;
        boolean isPassMid;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int count , int distance, boolean isPassMid) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.count = count;
            this.isPassMid = isPassMid;
        }
    }
}


