import java.util.*;
import java.io.*;
public class 벽부수고이동하기최단경로 {
    public static void main(String[] args) throws Exception{
        new 벽부수고이동하기최단경로().solution();
    }


    int R;
    int C;

    char[][] map;

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    static char WALL = '1';
    static char PATH = '0';
    boolean[][][] visited; // 벽을 부순 여부에 따른 방문 체크
    int minVal;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[2][R][C];

        for(int i = 0; i < R; i++){
            String input = br.readLine();
            for(int j = 0; j < C; j++){
                map[i][j] = input.charAt(j);
            }
        }
        bfs();



    }

    public void bfs(){
        Deque<Node> que = new ArrayDeque<>();
        que.offer(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x == C-1 && cur.y == R-1){ // 목적지에 도착했다면 결과를 출력하고 종료
                System.out.println(cur.cnt);
                return;
            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(nx >=  0 &&  ny >= 0 && nx < C && ny < R ){
                    if(map[ny][nx] == WALL){ // 벽이라면
                        if(cur.crash == 0){ // 아직 부수지 않은 경우이면서 부순상태에서 방문한 적이 없는 경우
                            que.offer(new Node(nx, ny, cur.cnt+1, 1));
                            visited[1][ny][nx] = true;

                        }

                    }else if(map[ny][nx] == PATH && !visited[cur.crash][ny][nx]){ // 벽이 아니면서 방문한 적이 없다면
                        que.offer(new Node(nx, ny, cur.cnt+1, cur.crash));
                        visited[cur.crash][ny][nx] = true;
                    }


                }
            }


        }
        System.out.println(-1);// 도착을 못한다면 -1을 반환

    }

    class Node{
        int x;
        int y;
        int crash; // 벽을 부신 적이 있는지 여부 (0 : 없음 , 1 : 있음)
        int cnt; // 이동 카운트
        public Node(int x, int y, int cnt, int crush){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.crash = crush;
        }
    }
}
