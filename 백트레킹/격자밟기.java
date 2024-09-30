import java.util.*;
import java.io.*;

public class 격자밟기 {
    public static int[] dx = new int[]{0, 0, -1, 1};
    public static int[] dy = new int[]{-1, 1, 0, 0};
    public static int cnt = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int totalCnt = 25 - (K + 2);  // 전체 남은 칸 (25칸에서 고정된 2칸과 K개의 장애물 제외)
        int map[][] = new int[5][5];
        
        // 장애물 입력 받기
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;  // 좌표를 0 기반으로 맞춤
            int x = Integer.parseInt(st.nextToken()) - 1;  // 좌표를 0 기반으로 맞춤
            map[y][x] = -1;  // 장애물 표시
        }
        
        boolean[][] visited = new boolean[5][5];
        visited[0][0] = true;  // 시작점 방문 처리
        visited[4][4] = true;  // 도착점 방문 처리


        dfs(map, totalCnt, 0, 0, 4, 4, visited);
        

        System.out.println(cnt);
    }

    // 두 위치를 모두 이동시키는 DFS
    public static void dfs(int[][] map, int totalCnt, int ar, int ac, int br, int bc, boolean[][] visited) {
        if (ar == br && ac == bc) {  // 두 위치가 같고
            if (totalCnt == 0) {  // 남은 칸이 없으면 성공
                cnt++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nar = ar + dx[i];
            int nac = ac + dy[i];

            if (isInvalid(nar, nac, map, visited)) {
                continue;
            }

            for (int j = 0; j < 4; j++) {
                int nbr = br + dx[j];
                int nbc = bc + dy[j];

                if (isInvalid(nbr, nbc, map, visited)) {
                    continue;
                }

                // 새로운 위치들 방문 처리
                visited[nar][nac] = true;
                visited[nbr][nbc] = true;

                // 두 위치가 같으면 1칸만 차지, 아니면 2칸 차지
                int remove = (nar == nbr && nac == nbc) ? 1 : 2;

                // DFS 재귀 호출
                dfs(map, totalCnt - remove, nar, nac, nbr, nbc, visited);

                // 백트래킹: 방문 해제
                visited[nar][nac] = false;
                visited[nbr][nbc] = false;
            }
        }
    }

    // 유효하지 않은 좌표 체크
    public static boolean isInvalid(int r, int c, int[][] map, boolean[][] visited) {
        if (r < 0 || r >= 5 || c < 0 || c >= 5 || visited[r][c] || map[r][c] == -1) {
            return true;
        }
        return false;
    }
}
