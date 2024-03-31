import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의합 {
    public static void main(String[] args) throws Exception{
        new 부분수열의합().solution();
    }

    public int[] number;

    public int target;
    public int N;
    public int cnt;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        number = new int[N];
        for(int i = 0 ; i < N; i ++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        cnt = 0;

        dfs(0, 0);
        if(target == 0){
            cnt--; // 합이 0일 경우 sum의 시작을 0으로 시작하기 때문에 아무 것도 없는 부분 수열의 경우도 0이 되므로 이 경우를 뺀다.
        }
        System.out.println(cnt);


    }

    public void dfs(int depth, int sum){
        if(depth == N){ // 하나의 케이스 다 탐색했으면 그 케이스가 정답 케이스에 해당하는지 확인
            if(sum == target){
                cnt++;
            }
            return;
        }
        dfs(depth+1, sum+number[depth]); //해당 숫자를 선택 안하는 경우
        dfs(depth+1, sum); //선택하는 경우


    }
}
