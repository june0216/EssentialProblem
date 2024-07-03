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
            // i 인덱스의 값이 의미하는 것은 ( i ~ i+1 ) 사이의 범위를 의미한다. 이에 따라 i ~ i+K 범위를 구하기 위해서는 end값이 sum 배열에서 (i+K-1) 인덱스는 i+K-1 ~ i+K 범위 이므로 이므로 해당 인덱스 값을 꺼내야 i+K 범위까지가 된다.
            if(sum[i+K-1] - sum[i-1] > max){ // 구간 합이 제일 큰 부분을 구하기
                answerStart = i;
                answerEnd = i+K; // 끝 부분을 나타내는 i+K로 저장한다.
                max = sum[i+K-1] - sum[i-1]; // i+K까지를 구하기 위해서는 i+K-1인덱스의 값을 꺼내야한다. 이는 i+K-1 ~ i+K 범위를 나타낸다.
            }
        }

        System.out.println(answerStart + " " + answerEnd);
    }
}
