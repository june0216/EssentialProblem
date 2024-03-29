import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전0 {
    public static void main(String[] args) throws Exception{
        new 동전0().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int total = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] coins = new int[total];
        for(int i= 0 ; i < total; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        int result = 0;
        for(int i = total-1;i >=0 ;i-- ){ // 큰 값부터 동전 세기 -> 작은 단위가 큰 단위의 배수이기 때문에 그리디로 하면 된다.
            int temp = target/coins[i]; // 금액에서 동전 단위를 나누면 동전이 몇개 필요한지 알 수 있다.
            if(temp >0){ // 나눌 수 있으면
                result += temp; // 동전의 갯수 업데이트
                target = target - (coins[i] * temp); // 동전을 쓰고 남은 금액 업데이트
            }
        }
        System.out.println(result);
    }
}
