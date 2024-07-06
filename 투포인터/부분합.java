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
        int[] num = new int[N+1];

        for(int i = 0 ; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }


        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        int sum = 0;
        while(start <= end && end <= N) { // start <= end 가 유지되어야 한다. + end은 배열의 끝 보다 작아야 한다.
            // end값이 N과 같을 때까지 하는 이유 -> end 포인터가 배열의 끝을 넘었더라도, start 포인터를 이동시키면서 마지막 부분 배열을 고려할 수 있음
            if(sum < target) { // 목표 값보다 작으면 인덱스 증가한다.
                sum += num[end++];
            } else if(sum >= target) { // 목표 값보다 크면 범위를 줄여야 한다.
                len = Math.min(len, end-start);
                sum -= num[start++];
            }
        }
        System.out.println(len==Integer.MAX_VALUE ? 0 : len);



    }



}
