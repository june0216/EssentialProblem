import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 블로그 {
    public static void main(String args[]) throws Exception{
        new 블로그().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int len= Integer.parseInt(st.nextToken());

        int visit[] = new int[N+1];
        int sum[] = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            visit[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            sum[i] = visit[i] + sum[i-1];
        }
        int max = 0;
        int cnt = 0;
        for(int i = len; i <= N; i++){
            if(sum[i] - sum[i- len] == max){
                cnt++;
            }
            if(sum[i] - sum[i- len] > max){
                max = sum[i] - sum[i- len];
                cnt = 1;
            }
        }
        if(max == 0){
            System.out.println("SAD");
        }else{
            System.out.println(max);
            System.out.println(cnt);
        }





    }

}
