import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 모든순열 {
    public static void main(String[] args ) throws Exception {
            new 모든순열().solution();
    }

    int N;
    StringBuilder sb;
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        visited = new boolean[N+1];
        num= new int[N];// 각 횟수에 자기가 방문한 숫자를 넣는다.

       perm(0); // cnt를 넘긴다. // 0부터 시작해야한다. 시작점이 다 다르기 때문
        System.out.println(sb);

    }
    boolean[] visited;
    int[] num;

    public void perm(int cnt){
        if(cnt == N){
            for(int i = 0; i <N; i++){
                sb.append(num[i]).append(" ");

            }
            sb.append("\n");
            return;
        }


        for(int i = 1 ;i <= N; i++){
            if(!visited[i]) {
                num[cnt] = i;
                visited[i] = true;
                perm( cnt+1);
                visited[i] = false;
            }



        }

    }
}
