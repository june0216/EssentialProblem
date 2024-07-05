import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class 부분합 {
    public static void main(String[] args) throws Exception{
        new 부분합().solution();

    }
    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        int[] num = new int[N];

        for(int i = 0 ; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }


        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        int sum = 0;
        while(start <= end && end <= N) {
            if(sum < target) {
                sum += num[end++];
            } else if(sum >= target) {
                len = Math.min(len, end-start);
                sum -= num[start++];
            }
        }
        System.out.println(len==Integer.MAX_VALUE ? 0 : len);



    }



}
