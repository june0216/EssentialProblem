import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수열 {
    // 연속적인 며칠 동안의 온도의 합이 가장 큰 값
    public static void main(String[] args) throws Exception {
        new 수열().slidingWindow();
    }


    // 슬라이딩 윈도우
    public void slidingWindow() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] num= new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;

        for(int i = 0; i < K ;i++){
            sum+= num[i];
        }
        long max = sum;
        int start = 0;
        int end = K;
        while(end <N+1){
            sum -= num[start++]; // 앞에 있는 부분 빼기 -> 0 ~ N-K까지
            sum += num[end++]; // 뒤에 있는 부분 더하기 -> K~N까지
            max = Math.max(sum, max);

        }
        System.out.println(max);

    }

    // 누적합
    public void prefixSum() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] num= new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        long[] prefixSum = new long[N+1];
        prefixSum[0] = num[0];
        for(int i = 1; i < N ;i++){
            prefixSum[i]+= prefixSum[i-1] + num[i];
        }

        long max = 0;
        for(int i = 0 ; i < N-K; i++){
            long rangeSum = prefixSum[K-i-1] - prefixSum[i];
            max = Math.max(max, rangeSum);
        }

        System.out.println(max);
    }
}
