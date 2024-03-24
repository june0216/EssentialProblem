import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 추월 {

    int N;
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Deque<String> que = new ArrayDeque<>();
       for(int i = 0 ; i < N; i++){
           String name = br.readLine();
           que.offer(name); // 큐에 저장
       }
       int count = 0;
       for(int i = 0; i < N; i++){
           String name = br.readLine();
           if(que.peek().equals(name)){ // 꺼냈는데 순서에 맞게 나왔으면 추월한 것이 아님
               que.pop(); // 다음 순서를 위해 꺼냄
           }else{ // 추월했다면 개수를 세어주고 큐에서 삭제
               que.remove(name);
               count++;
           }
       }

        System.out.println(count);
    }
    public static void main(String[] args) throws Exception{
        new 추월().solution();
    }
}
