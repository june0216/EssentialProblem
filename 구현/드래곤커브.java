import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class 드래곤커브 {
    public static void main(String[] args) throws Exception{
        new 드래곤커브().solution();
    }


    int dy[] = {0, -1, 0, 1}; // → ↑ ← ↓
    int dx[] = {1, 0, -1, 0};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean map[][] = new boolean[101][101];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int startDirection = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());
            map[y][x] = true;
            int cnt = 0;
            List<Integer> direction = new ArrayList<>();
            direction.add(startDirection);
            for(int d = 0;  d< generation; d++){
                int size = direction.size();
                for(int l = size-1; l >= 0; l--){ // 규칙 부분
                    direction.add((direction.get(l)+1) % 4);
                }
            }

           for(int d : direction){

               x += dx[d];
               y += dy[d];
               map[y][x] = true;
           }


        }

        int result = 0;
        for(int i = 0; i < 100;i++){
            for(int j = 0; j < 100; j++){
                if(map[i][j] && map[i+1][j]  && map[i+1][j+1]  && map[i][j+1]){

                    result++;
                }
            }
        }
        System.out.println(result);


    }

}
