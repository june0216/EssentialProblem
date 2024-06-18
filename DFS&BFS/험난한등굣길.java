import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.*;

public class 험난한등굣길 {

    // 최소 이동 횟수 구하기
    public static void main(String[] args) throws Exception{
        new 험난한등굣길().solution();
    }


    int R;
    int C;

    int areaNum;

    int[][] map;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        areaNum = Integer.parseInt(br.readLine());


        map = new int[R][C];
        for(int i = 0; i < areaNum; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            check(r-1, c-1, a); // 장애물 있는 부분의 모서리 부분을 체크해둠
        }

        bfs();



    }


    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};

    int[][] diagonal = {{-1, 1},{1, 1},{1, -1},{-1, -1}}; //사각형의 네 모서리로, 각 모서리에서 다음 모서리로 이동할 때 사용
    public  void check(int y, int x, int d) {
        map[y][x] = -1;
        int ny = y;
        int nx = x - d;

        for(int i=0; i<4; i++) { //diagonal 배열을 사용하여 네 방향 각각을 처리
            for(int j=0; j<d; j++) { //각 방향에서 d번 만큼 점을 이동
                nx += diagonal[i][1];
                ny += diagonal[i][0];
                if(!isRange(nx, ny)) continue;
                map[ny][nx] = -1;
                System.out.println(ny + " " + nx);
            }
        }
    }




    public void bfs(){
        Deque<Node> que = new ArrayDeque<>();
        boolean visited[][] = new boolean[R][C];
        que.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x == C-1 && cur.y == R-1){
                System.out.println("YES");
                System.out.println(cur.cnt);
                return;
            }
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                if(isRange(nx, ny) && map[ny][nx] != -1 && !visited[ny][nx]){
                    que.offer(new Node(nx, ny, cur.cnt+1));
                    visited[ny][nx] = true;
                }
            }
        }
        System.out.println("NO");

    }

    public boolean isRange(int x, int y){
        if(x >= 0 && y >= 0 && x < C && y < R){
            return true;
        }
        return false;
    }


    class Node{
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;

        }


    }
}
