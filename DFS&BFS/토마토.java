import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 토마토 {

    public static final int[] dx = new int[]{1, -1, 0, 0};
    public static final int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        new 토마토().solution();
    }


    static class Pos{
        int x;
        int y;
        int day;
        public Pos(int x, int y, int day){
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int[][] tomato = new int[height][width];

        Deque<Pos> pq = new ArrayDeque<>();
        int total = 0;
        for(int i = 0; i < height; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < width; j++){
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if(tomato[i][j] == 1){ // 익은 토마토들 큐에 담기
                    pq.offer(new Pos(j, i, 0));
                }
                if(tomato[i][j] == 0){ // 안익은 토마토 개수 세기
                    total++;
                }
            }
        }

        int endDay = 0;
        while(!pq.isEmpty()){
            Pos pos = pq.poll();
            if(pos.day > endDay){
                endDay = pos.day; // 다 익은 날짜 갱신
            }
            for(int i = 0 ; i < 4; i++){ // 4방을 확인하기
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if(nx >= 0 && nx < width && ny >= 0 && ny < height && tomato[ny][nx] == 0){ // 안익은 토마토
                    tomato[ny][nx] = 1; // 토마토 익히기
                    total--; // 안익은 토마토 개수 갱신
                    pq.offer(new Pos(nx, ny, pos.day+1)); // 큐에 넣기
                }
            }
        }
        if (total > 0) { // 안익는 상황이면
            System.out.println(-1);
        }else{ // 다 익은 상황이라면
            System.out.println(endDay);
        }



    }
}
