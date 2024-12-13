import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.*;
import java.util.StringTokenizer;

public class 가장긴부분수열5두번째 {
    public static void main(String[] args) throws Exception{
        new 가장긴부분수열5두번째().solution();
    }
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        int[] upperArrIdx = new int[N];
        dp[0] = arr[0];
        upperArrIdx[0] = 0;
        int last =0;


        for(int i = 1; i < N; i++){
            if(arr[i] > dp[last]){
                dp[++last] = arr[i];
                upperArrIdx[i] = last;
                continue;

            }
            int idx = binarySearch(arr[i],dp, last);
            dp[idx] = arr[i];
            upperArrIdx[i] = idx;


        }


        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = N-1; i >= 0; i--){
            if(last == upperArrIdx[i]){
                stack.addFirst(arr[i]);
                last--;
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append(stack.size()).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);



    }

    public int binarySearch(int num, int[] dp, int cur){ // lower
        int start =0;
        int end = cur+1;
        while(start < end){
            int mid = (start + end)/2;
            if(dp[mid] < num){ // 작을 때 -> 배열에 넣기
                start = mid+1;

            }else{ // 클때 넣으면 오름차순이 아닐 수 있음
                end = mid;
            }
        }
        return start;

    }


}
