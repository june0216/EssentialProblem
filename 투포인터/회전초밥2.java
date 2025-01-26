import java.io.BufferedReader;
import java.io.*;
import java.util.*;


public class 회전초밥2 {
    public static void main(String[] args) throws Exception{
        new 회전초밥2().solution();
    }

    int[] dish;

    int N;
    int typeCnt;
    int len;

    int coupNum;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
        N = Integer.parseInt(st.nextToken());
        typeCnt = Integer.parseInt(st.nextToken());
        len = Integer.parseInt(st.nextToken());
        coupNum = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();
        dish =new int[N+len-1];
        for(int i = 0; i < N; i++){
            dish[i] = Integer.parseInt(br.readLine());

        }
        for(int i = 0; i < len-1; i++){
            dish[N+i] = dish[i];
        }

        map.put(coupNum, 1);


        int start = 0;
        int end = 0;
        int max = 0;


        // 최대한 구간 안에 쿠폰 번호가 없어야 함


        while(end < N+len-1){
            map.put(dish[end], map.getOrDefault(dish[end], 0)+1);
            end++;
            if(end-start == len){
                max = Math.max(max, map.size());
                if(map.get(dish[start]) == 1 && dish[start] != coupNum){
                    map.remove(dish[start]);
                }else{
                    map.put(dish[start], map.get(dish[start])-1);
                }

                start++;
            }




        }

        System.out.println(max);


    }
}
