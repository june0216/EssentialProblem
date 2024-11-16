import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 달팽이숫자 {

    public static void main(String args[]) throws Exception{
        new 달팽이숫자().solution();
    }

    int dy[] = {1, 0, -1, 0};
    int dx[] = {0, 1, 0, -1};
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int time = 1;
        while(time <= T){
            int size = Integer.parseInt(br.readLine());
            int[][] map = new int[size+1][size+1];

            int direction = 0;
            int num = 1;
            map[0][0] = num;
            int nx = 0;
            int ny =0;
            num++;
            while (true){
                if(num > size*size) break;
                int nextX = nx + dy[direction%4];
                int nextY = ny + dx[direction%4];

                if(nextX >= 0 && nextY >= 0 && nextX <size && nextY < size && map[nextY][nextX] == 0){
                    map[nextY][nextX] = num;
                    num++;
                    nx = nextX;
                    ny = nextY;

                }
                else {
                    direction++;
                }


            }
            sb.append("#").append(time).append("\n");
            for(int i = 0; i < size;i++){
                for(int j = 0; j < size; j++){
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            time++;

            
        }
        System.out.println(sb);
    }
}
