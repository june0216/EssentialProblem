import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import java.io.*;

public class 거짓말 {
    public static void main(String[] args) throws Exception {
        new 거짓말().solution();
    }


    int[] parent;
    int[] rank;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> upper = new ArrayList<>(); // 이상이라고 한 경우 -> 큰 경우를 우선순위
        List<Integer> down = new ArrayList<>(); // 이하라고 한 경우  -> 큰 경우를 우선순위
        // 모순을 찾는 방법
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] person = new int[N+1];
        for(int i = 1; i<= N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num > 0){
                upper.add(num);
            }else{
                down.add(num);
            }
            person[i] = num;
        }

        Collections.sort(upper);
        Collections.sort(down);
        List<Integer> result = new ArrayList<>();

        // 거짓말을 i명으로 하자 (거짓말을 몇명이 해야하는지 구해야하므로 이를 기준으로 삼는다)
        // 이떄 거짓말을 아예 안하는 사람도 있으므로 이도 포함하기
        for(int i =0; i <= N ;i++){
            int res1 = upper.size() - findTruth(upper, i, N);
            int res2 = down.size() - findTruth(down, -1*i, N);
            if(res1+res2 == i){
                result.add(i);
            }

        }
        StringBuilder sb =new StringBuilder();
        sb.append(result.size()).append("\n");
        for(int n : result){
           sb.append(n).append(" ");
        }


        System.out.println(sb);



    }

    public int findTruth(List<Integer> li , int correct, int N){
        int start = 0;
        int end = li.size();
        int answer = 0;
        while(start < end){
            int mid = (start + end)/2; // 몇 명이 거짓말했는지.
            if(li.get(mid) <= correct) {
                start = mid+1;

            }else{
                end = mid;
            }
        }
        return start;

    }
}
