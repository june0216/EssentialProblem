import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 랜선자르기 {

    public static void main(String[] args) throws Exception{
        new 랜선자르기().solution();
    }
    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalNum = Integer.parseInt(st.nextToken());
        int targetNum = Integer.parseInt(st.nextToken());
        int[] wire = new int[totalNum];
        long max = 0; // 이분 탐색을 할 때 초반에 end값을 주어진 간선 중 가장 큰 값으로 설정하기 위해
        for(int i = 0 ; i < totalNum; i++){
            wire[i] = Integer.parseInt(br.readLine());
            if(max < wire[i]) max = wire[i];
        }
        long start = 1;
        long end = max;

        while(start <= end){
            long mid = start + ((end-start)/2); // 길이를 설정한다.
            long divided = 0;
            for(int num : wire){
                divided += num /mid;  // 해당 길이에 맞게 간선들을 자른다.
            }


            if(targetNum > divided){ // 그 개수가 목표한 개수보다 작다면 길이를 줄여서 개수를 늘려야 한다.
                end = mid-1; // 길이를 줄이기

            }else{
                start = mid+1;// 그 개수가 목표한 개수보다 크다면 일단 정답이고 최대 길이를 찾기 위해 길이를 키운다.

            }
        }
        System.out.println(end);

    }



}
