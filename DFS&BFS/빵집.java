import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class 빵집 {
    //이 경로는 겹칠 수 없고, 서로 접할 수도 없

    public static void main(String[] args) throws Exception{
        new 빵집().solution();
    }


    int R;
    int C;


    char[][] map;

    int[] dx= {1, 1, 1}; // 오른쪽 대각선 위, 오른쪽, 오른쪽 대각선 아래
    int[] dy = {-1, 0, 1};

    boolean[][] visited;
    int s;

    int res;

    int cnt;
    public void solution() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0 ; i< R; i++){
            String input = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        cnt = 0;
        s = 0;
        for(int i = 0; i < R; i++){
            dfs(0, i);
            res = 0;

        }
        System.out.println(cnt);



    }

    //겹칠 수 없고, 서로 접할 수도 없다 -> visited로 관리하면 된다.

    public void dfs(int startX, int startY){
        if(startX == C-1){
            res = 1; // 목적지(빵집)까지 왔으므로 경로 1추가
            cnt++;
            visited[startY][startX] = true;
            return;
        }
        for(int i = 0; i < 3; i++){
            int nx = dx[i] + startX;
            int ny = dy[i] + startY;
            if(nx >= 0 && ny >=0 && nx < C && ny < R){
                if(map[ny][nx] != 'x' && !visited[ny][nx]){
                    dfs(nx, ny);
                    visited[ny][nx] = true; // 경로를 찾다가 해당 경로가 도착지에 도달하지 않으면 왔던 길을 모두 visited를 다시 false로 만들어야되지 않을까? 라는 생각을 했다. 하지만 위에서부터 순차적으로 차근차근 쌓는 형태로 경로를 탐색하고 있기 때문에
                    // 중간에 끊어져서 도착지점에 도착하지 못한 경로들은 다음 출발지의 경로에 영향을 주지 않으므로 다시 visited를 수정하지 않아도 된다.
                    if(res == 1){ // 이미 경로 추가되었다면 그냥 return
                        return;
                    }
                }


            }
        }

    }
}
