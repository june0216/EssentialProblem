import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

public class 두용액 {
    public static void main(String[] args) throws Exception {
        new 두용액().solution();

    }

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());



        List<Integer> liquid = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());

            liquid.add(num);


        }

        // 완탐으로 하면 시간초과이므로 투 포인터를 이용하여 O(N) 시간 내에 한다.
        Collections.sort(liquid); // 정렬한다. ( 산성 ~ 알칼리성 ) 이렇게 정될 것이다.

        int start = 0;
        int end = N-1;
        int min =Integer.MAX_VALUE; // 두 수의 차이의 최솟값을 저장
        int answer1 = 0;
        int answer2 = 0;
        while(start < end){
            int gap = liquid.get(start) + liquid.get(end);

            if(Math.abs(gap) < min){ // 이전에 구한 차이보다 더 적다면 업데이트한다. 이떄, 차이이기 때문에 절대값으로 구해준다.
                min = Math.abs(gap);
                answer1 = liquid.get(start);
                answer2 = liquid.get(end);
                if(min == 0) break; // 만약 그 차이가 0이라면 바로 탐색을 종료한다.

            }else if(gap <0){ // 만약 start 인덱스의 값이 더 오버했다면 더 차이를 좁히기 위해 start 인덱스를 옮긴다.
                start++;
            }else{
                end--;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer1).append(" ").append(answer2);
        System.out.println(sb);


    }
}
