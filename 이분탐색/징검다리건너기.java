import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class 징검다리건너기 {
    public static void main(String[] args) throws Exception{
        new 징검다리건너기().solution();
    }


    int N;
    int[] arr;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 최소한의 힘으로 끝까지 이동하기 
        // 처음에는 이분탐색으로 생각했다 하지만 arr 크기가 다 달라서 애매하다 

        // 하지만 그래도 이분탐색이었다. 
        // 이때, K가 가능한지 확인하는 로직이 어려웠다. -> bfs로 도달할 수 있다. 

        long start = 1;
        long end = calculate(0, arr.length-1);


        long result= 0;

        while(start<= end){
            long mid = start+ ((end-start)/2);
            if(isReach(mid, arr.length-1)){

                end = mid-1;
                result = mid;
            }else{
                start = mid+1;
                
            }
            
        }

        System.out.println(result);


    }



    public boolean isReach(long k, int target){
        Deque<Integer> que = new ArrayDeque<>();
        
        que.offer(0);
        boolean flag = false;

        boolean[] visited = new boolean[target+1];
        visited[0] = true;
        while(!que.isEmpty()){
            int cur = que.poll();
            if(cur == target){
                return true;
            }

            // 지금부터 끝까지 탐색
            for(int i = cur+1; i <= target; i++){
                if(visited[i]) continue;
                long dis = calculate(cur, i);
                if(dis<= k){
                    que.offer(i);
                    visited[i] = true;
                }
            }
        }

        return false;
    }


    public long calculate(int start, int end){
        return (long)(end-start) * (1+Math.abs(arr[start] - arr[end]));
        
    }
}
