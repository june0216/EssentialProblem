import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기 {

    public int[] dy = {1, -1, 0, 0}; // 동, 서, 북, 남으로 이동
    public int[] dx = {0, 0, -1, 1};

    int currentX;
    int currentY;

    int H;
    int W;

    int[] dice = new int[7];
    int[][] map;
    StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        currentX = Integer.parseInt(st.nextToken());
        currentY = Integer.parseInt(st.nextToken());

        int commandNum = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for(int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++ ){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < commandNum; i++){
            int command = Integer.parseInt(st.nextToken());
            move(command);

        }
        System.out.println(sb);


    }

    // 해당 명령어에 맞는 위치 찾기
    public void move(int command){
        int nx = dx[command-1]+ currentX;
        int ny = dy[command-1] + currentY;
        if(nx <0 || ny<0 || nx > H-1 || ny > W-1){
            return; // 범위를 넘어서면 출력 안함
        }else{
            roll(nx, ny, command); // 주사위를 굴리고 출력
            currentX = nx; // 현재 위치 업데이트
            currentY = ny;
        }

    }

    public void roll(int x, int y, int command){

        int temp = dice[3]; // 윗면
        switch(command){
            // 동쪽
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
                break;
                //서쪽
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
                break;

            case 3:
                // 북쪽
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = temp;
                break;
            case 4:
                //남쪽
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
                break;

        }
        if(map[x][y] == 0){ // 0이면 주사위에 있던 숫자 복사
            map[x][y] = dice[6];
        }else{ // 0이 아니면 바닥에 있는 숫자를 바닥면에 복사
            dice[6] = map[x][y];
            map[x][y] = 0; // 복사 후 0으로 세팅
        }

        sb.append(dice[3]).append("\n"); // 위의 면  출력

    }


    public static void main(String[] args) throws Exception{
        new 주사위굴리기().solution();
    }
}
