import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.*;

public class 연구소 {

    /**
     * 1) 벽 세우기
     * 2) 바이러스 퍼뜨리기
     * 3) 안전 지점 개수 세기
     */
    int H;
    int W;
    int[][] place;

    List<Pos> wall;

    long max;
    List<Pos> virus;
    boolean[][] visited;

    int[] dx = new int[]{0, 0, -1, 1};
    int[] dy = new int[]{-1, 1, 0, 0};
    int[][] copyPlace;

    class Pos{
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        place = new int[H][W];
        wall = new ArrayList<>();
        copyPlace = new int[H][W];
        virus = new ArrayList<>();

        for(int i = 0 ; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< W; j++){
                place[i][j] = Integer.parseInt(st.nextToken());
                if(place[i][j] == 0){ // 벽을 세울 수 있는 지점이라면
                    wall.add(new Pos(i, j));
                }
                if(place[i][j] == 2){
                    virus.add(new Pos(i, j));
                }
            }
        }

        for(int i = 0; i < wall.size(); i++){ //3개의 조합을 만들기 위해 for문으로
            place[wall.get(i).x][wall.get(i).y] = 1;
            for(int j = i+1; j < wall.size(); j++){
                place[wall.get(j).x][wall.get(j).y] = 1;
                for(int k = j+1; k < wall.size(); k++){

                    visited = new boolean[H][W];
                    place[wall.get(k).x][wall.get(k).y] = 1;
                    for(int c = 0; c < H; c++){ // 깊은 복사
                        for(int c1 = 0; c1 < W; c1++){
                            copyPlace[c][c1] = place[c][c1];
                        }
                    }
                    bfs(); // 바이러스 퍼뜨리기
                    long result = Arrays.stream(copyPlace)
                            .flatMapToInt(Arrays::stream)
                            .filter(num -> num==0)
                            .count();
                    if(max < result){
                        max = result;
                    }
                    place[wall.get(k).x][wall.get(k).y] = 0;
                }
                place[wall.get(j).x][wall.get(j).y] = 0;
            }
            place[wall.get(i).x][wall.get(i).y] = 0;
        }

        System.out.println(max);


    }
    public void bfs(){
        Deque<Pos> que = new ArrayDeque<>();
        for(Pos pos : virus){
            que.offer(pos);
            visited[pos.x][pos.y] = true;
        }

        while(!que.isEmpty()){
            Pos current = que.poll();
            for(int i = 0; i < 4; i++){
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < H && ny <W && !visited[nx][ny] && copyPlace[nx][ny] == 0){
                    visited[nx][ny] = true;
                    que.offer(new Pos(nx, ny));
                    copyPlace[nx][ny] = 2;
                }
            }
        }

    }
    public static void main(String[] args) throws Exception{
        new 연구소().solution();
    }
}
