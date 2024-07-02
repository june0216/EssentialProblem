import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스터디시간정하기1 {
    public static void main(String[] args) throws Exception{
        new 스터디시간정하기1().solution();

    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K  = Integer.parseInt(st.nextToken());
        int[] time = new int[100001];
        while(N --> 0){
            int cnt = Integer.parseInt(br.readLine());
            for(int i = 0 ; i< cnt; i++){
                st = new StringTokenizer(br.readLine());
                int start =Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                time[start]++;
                time[end]--;

            }
        }

        for(int i = 1; i <= 100000; i++){ // 스위핑하기
            time[i] = time[i] + time[i-1];
        }


        int[] sum= new int[100001]; // 구간합을 구하기
        sum[0] = time[0];
        for (int i = 1; i <= 100000; i++) {
            sum[i] = sum[i - 1] + time[i]; // 순차적으로 구간합 구하기
        }

        int answerStart = 0;
        int answerEnd = K-1;
        int max = sum[K-1];
        for(int i = 1 ;i <= 100000-K; i++){
            if(sum[i+K-1] - sum[i-1] > max){ // 구간 합이 제일 큰 부분을 구하기
                answerStart = i;
                answerEnd = i+K;
                max = sum[i+K-1] - sum[i-1];
            }
        }

        System.out.println(answerStart + " " + answerEnd);
    }
}
