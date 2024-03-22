import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 감시 {

    int H;
    int W;



    List<Pos> cctv;
    int result;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[][] office = new int[H][W];

        cctv = new ArrayList<>();
        result = Integer.MAX_VALUE;
        for(int i =0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++){
                String input = st.nextToken();
                office[i][j] = Integer.parseInt(input);
                if(office[i][j] >=1 && office[i][j] <= 5){
                    cctv.add(new Pos(i, j));
                }
            }
        }

        dfs(0, office);



        System.out.println(result);


    }

    public void dfs(int cnt, int[][] map){ // 재귀함수로 90도 돌린 씨씨티비 상태를 각각 조합하여 상태를 체크해본다.
        if(cnt == cctv.size()){
            checkNum(map);
            return;
        }

        Pos pos = cctv.get(cnt);
        int num = map[pos.x][pos.y];
        int[][] tmp = new int[H][W];
        switch (num){
            case 1:
                tmp=copyOffice(map);
                changeRight(pos.x, pos.y, tmp); // 1번째 케이스
                dfs(cnt+1, tmp); // 다음 씨씨티비로


                tmp = copyOffice(map); // 씨씨티비 cnt번째 상태롤 복사한다. (cnt당 여러 케이스를 시도해야하기 때문)
                changeDown(pos.x, pos.y,tmp); // 2번째 케이스
                dfs(cnt+1, tmp); // 다음 씨씨티비로

                tmp = copyOffice(map);
                changeLeft(pos.x, pos.y,tmp); //3번째 경우의 수
                dfs(cnt+1, tmp);

                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp); // 4번째 경우의 수
                dfs(cnt+1, tmp);

                break;
            case 2:
                tmp = copyOffice(map);
                changeRight(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeDown(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                break;
            case 3:
                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeRight(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                tmp = copyOffice(map);
                changeDown(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                tmp = copyOffice(map);
                changeDown(pos.x, pos.y,tmp);
                changeRight(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);


                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);
                break;
            case 4:
                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeDown(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);


                tmp = copyOffice(map);
                changeDown(pos.x, pos.y,tmp);
                changeRight(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeRight(pos.x, pos.y,tmp);
                changeDown(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                tmp = copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeRight(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);

                break;
            case 5: // 5번 씨씨티비 같은 경우 회전을 해도 다 같은 경우밖에 없으므로 1번만 해도 된다.
                tmp=copyOffice(map);
                changeUp(pos.x, pos.y,tmp);
                changeRight(pos.x, pos.y,tmp);
                changeLeft(pos.x, pos.y,tmp);
                changeDown(pos.x, pos.y,tmp);
                dfs(cnt+1, tmp);
                break;
        }
    }

    public void checkNum(int[][] office){
        int temp = 0;
        for(int i = 0 ; i < H; i++){
            for(int j = 0; j < W; j++){
                if(office[i][j] == 0){
                    temp++;
                }
                //office[i][j] = origin[i][j]; // 원상복구
            }
        }
        if(result > temp){
            result = temp;
        }
    }

    public void changeRight(int x, int y, int[][] office){

        for(int i = y+1; i < W; i++){
            if(office[x][i] == 6) return;
            if(office[x][i] >=1 && office[x][i] <= 5){
                continue;
            }
            office[x][i] = -1;
        }
    }

    public int[][] copyOffice(int[][] origin){
        int[][] office = new int[H][W];
        for(int i = 0 ; i < H; i++){
            for(int j = 0; j < W; j++){

                office[i][j] = origin[i][j]; // 원상복구
            }
        }
        return office;

    }

    public void changeUp(int x, int y, int[][] office){

        for(int i = x-1; i >= 0 ; i--){

            if(office[i][y] == 6) return;
            if(office[i][y] >=1 && office[i][y] <= 5){
                continue;
            }
            office[i][y] = -1;
        }
    }
    public void changeDown(int x, int y, int[][] office){

        for(int i = x+1; i < H; i++){
            if(office[i][y] == 6) return;
            if(office[i][y] >=1 && office[i][y] <= 5){
                continue;
            }
            office[i][y] = -1;
        }
    }

    public void changeLeft(int x, int y, int[][] office){

        for(int i = y-1; i >= 0; i--){
            if(office[x][i] == 6) return;
            if(office[x][i] >=1 && office[x][i] <= 5){
                continue;
            }
            office[x][i] = -1;
        }
    }

    class Pos{
        int x;
        int y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception{
        new 감시().solution();
    }
}
