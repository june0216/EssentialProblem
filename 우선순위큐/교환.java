import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 교환 {

    public static void main(String[] args) throws Exception{
        new 교환().solution();
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String input = st.nextToken();

        char[] number = input.toCharArray();

        int L = number.length;


        int start = Integer.parseInt(input);


        int k = Integer.parseInt(st.nextToken());
        boolean visited[][] = new boolean[k+1][1000001];
        Deque<Integer> que = new ArrayDeque<>();

        que.offer(start);

        while(!que.isEmpty() && k > 0){
            int currentSize = que.size(); // 특정 회차에 교환한 수의 경우들을 모두 확인하면서 또 교환한다.
            for(int i = 0 ;i < currentSize; i++){
                int cur = que.poll();
                for(int c = 0 ;c < L-1; c++){ // 그리디로는 불가능하므로 각 횟수별 완탐으로 해야한다.
                    for(int j = c +1; j < L;j++ ){
                        int result = swap(c, j, cur);

                        if(result == 0 || visited[k][result]) continue; // 해당 회차에 해당 숫자를 만든 경우가 있다면 패스한다.
                        que.offer(result);
                        visited[k][result] = true;

                    }
                }
            }
            k--;
        }

        int res = 0;

        if(que.isEmpty()){ // 비어있다는 것은 k번 교환할 수 없다는 것이므로 -1
            System.out.println(-1);
        }else{
            while(!que.isEmpty()){ // k번째 교환한 숫자들을 다 확인하여 그 중 가장 큰 값을 출력한다.
                int cur = que.poll();

                res = Math.max(res, cur);
            }
            System.out.println(res);
        }

    }


    public int swap(int i, int j, int num){
        char[] change = String.valueOf(num).toCharArray();

        char c = change[i];
        change[i] = change[j];
        change[j] = c;
        if(change[0] == '0') return 0; // 0으로 시작하면 0을 반환

        return Integer.parseInt(new String(change));

    }


}
