import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 수들의합 {
    public static void main(String[] args) throws Exception{
        new 수들의합().solution();
    }

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 모든 경우의 수이지만 2중 for문으로 하면 별로 
        int N= Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken());


        long[] presum = new long[N+1];
        long[] arr = new long[N+1];
        long cnt = 0;
        st = new StringTokenizer(br.readLine());

        /*
         for (int i = 1; i <= n; i++) {
            arr[i] = arr[i-1] + Long.parseLong(st.nextToken());
        }
        */
        for(int i = 1; i <= N; i++){
            arr[i] = Long.parseLong(st.nextToken());
            
        }


        
        for(int i = 1; i <= N; i++){
            presum[i] = presum[i-1]+ arr[i];
           // System.out.println(presum[i]);
        }

        // 길이 + 합

        // 2중 for문 -> 시간초과 
        // 투포인터가 안되는 게 음수도 있어서 안됨
        // i ~ j 까지의 합
        /*
        for(int i = 0; i <= N; i++){
            for(int j = i+2; j <= N; j++){
                //System.out.println(presum[j] - presum[i]);
                if((presum[j] - presum[i]) == target){
                    cnt++;
                }
            }

            //System.out.println("\n");
        }*/

        Map<Long, Long> map = new HashMap<>();

        map.put(0l, 1l); // 시작 인덱스가 0이 아닌 다른 지점(j+1)부터 시작하는 부분 배열의 합이 K가 되는 경우를 효율적으로 찾긱 위함

        for(int i = 1;i <= N; i++){
            // K가 되는 경우의 수 
            cnt += map.getOrDefault(presum[i]-target, 0l);

            // 지금까지의 누적합 카운트
            map.put(presum[i], map.getOrDefault(presum[i], 0l)+1);
        }



        

        System.out.println(cnt);

        


        
    }
}
