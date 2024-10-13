import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 치킨배달 {

    public static void main(String[] args) throws Exception{
        new 치킨배달().solution();
    }


    public static int CHICKEN = 2;
    public static int HOUSE = 1;
    List<int[]> chicken;
    List<int[]> house;
    int[][] map;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 살릴 치킨집의 개수
        map = new int[N][N];

        house = new ArrayList<>();
        chicken = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == HOUSE){
                    house.add(new int[]{i, j});
                }else if(map[i][j] == CHICKEN){
                    chicken.add(new int[]{i, j});
                }
            }
        }

        selected = new boolean[chicken.size()];

        combination(0, 0, M);
        System.out.println(min);


    }

    int min = Integer.MAX_VALUE;
    boolean[] selected;
    public void combination(int start, int cnt, int M){
        if(cnt == M){
            calculate();
            return;
        }

        if(start >= chicken.size()){
            return;
        }

        // 선택하는 경우
        selected[start] = true;
        combination(start+1, cnt+1, M);

        // 선택 안하는 경우
        selected[start] = false;
        combination(start+1, cnt, M);

    }
    public void calculate(){
        int result = 0;

        for(int j = 0; j < house.size(); j++){
            int temp = Integer.MAX_VALUE;
            for(int i = 0;  i < chicken.size(); i++){
                if(!selected[i]) continue;
                int dis = calulateDistance(house.get(j)[0], house.get(j)[1], chicken.get(i)[0], chicken.get(i)[1]);
                temp = Math.min(temp, dis);

            }
            result += temp;

        }
        min = Math.min(result, min);
    }

    public int calulateDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1- x2) + Math.abs(y1 - y2);

    }
}
