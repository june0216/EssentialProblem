import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 내리막길 {
    public static void main(String[] args) throws Exception{
        new 내리막길().solution();

    }

    int N;
    int M;
    int[][] route;
    int[][] map;
    /*
    dfs를 사용하되, 메모리제이션을 사용한 기법
    왜냐하면 출발지는 똑같은데 다른 경로들을 찾기 위함
     */
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        route = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(route[i], -1);
        }



        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(route[i][j]).append(" ");
            }
            sb.append("\n");
        }

        dfs(0, 0);


        System.out.println(route[0][0]);


    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    // 재귀로 자신의 경로 개수를 반환하여 누적한다.
    public int dfs(int x, int y ){
        // 내리막길이 있는 라우트 모두 세기 -> 매번 출발점에서 세는 것이 아니라 분기하는 것임
        if(x == M-1 && y == N-1){
            return 1;
        }

        if(route[y][x] != -1){ // 이미 방문한 경우에 메모리제이션 -> 미리 끌어다 쓴다.
            return route[y][x];
        }


        // 처음 방문한 것이라면
        route[y][x] = 0;
        for(int i = 0; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx >= 0 && ny >= 0 && ny < N && nx <M){

                // visited 검사를 안하기 때문에 하나의 최단 경로로 마무리하는 게 아니라 여러 경로를 갈 수 있다.
                if(map[ny][nx] < map[y][x]){ // 작으면 끝까지 이동한 다음에 여기서 경로가 얼마나 있는지 업데이트

                    route[y][x] += dfs(nx, ny); // 자기 노드 입장에서 경로가 몇개 있는지 확인
                }
            }
        }
        // 자기 위치에서 다 계산한 후
        return route[y][x];
    }
}
